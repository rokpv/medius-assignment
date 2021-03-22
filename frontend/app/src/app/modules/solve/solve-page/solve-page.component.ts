import { Component, OnInit } from '@angular/core';
import {ApiService} from '../../../services/api.service';
import {Problem} from '../../../models/Problem';
import {Solution} from '../../../models/Solution';
import {ProblemViewComponent} from '../../../components/problem-view/problem-view.component';

@Component({
  templateUrl: './solve-page.component.html',
  styleUrls: ['./solve-page.component.scss']
})
export class SolvePageComponent implements OnInit {

  problems: Problem[] = [];
  selectedProblem: Problem | null = null;

  grid: number[] = [];

  solution: Solution | null = null;

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.apiService.getProblems().subscribe(p => {
      this.problems = p;
      this.selectProblem(p[0]);
    });
  }

  selectProblem(problem: Problem): void {
    this.grid = this.parseGrid(problem);
    this.selectedProblem = problem;
    this.solution = null;
  }

  /**
   * Change light states according to game rules
   */
  onSquareClicked(index: number): void {
    if (!this.selectedProblem) {
      console.warn('Selected problem is null.');
      return;
    }
    const n = this.selectedProblem.size;

    this.flipSquare(index);

    // Fix for incorrect lighting on left and right wall
    if (index % n === 0) {
      this.flipSquare(index + 1);
      this.flipSquare(index - n);
      this.flipSquare(index + n);
    } else if ((index + 1) % n === 0) {
      this.flipSquare(index - 1);
      this.flipSquare(index - n);
      this.flipSquare(index + n);
    } else {
      this.flipSquare(index + 1);
      this.flipSquare(index - 1);
      this.flipSquare(index - n);
      this.flipSquare(index + n);
    }
  }

  /**
   * Fetch solution for chosen problem from server
   */
  getSolution(problemView: ProblemViewComponent): void {
    if (!this.selectedProblem) {
      console.warn('Selected problem is null!');
      return;
    }

    this.resetGrid(problemView);
    this.apiService.getSolutionByProblemId(this.selectedProblem.id).subscribe(s => {
      this.solution = s;
    });
  }

  /**
   * Transforms a problem's description string into an array.
   *
   * "1010" => [1, 0, 1, 0]
   */
  parseGrid(problem: Problem): number[] {
    return problem.description.split('').map(s => parseInt(s, 10));
  }

  resetGrid(problemView: ProblemViewComponent): void {
    if (!this.selectedProblem) {
      console.warn('Selected problem is null!');
      return;
    }

    this.solution = null;
    problemView.resetSolution();
    this.selectProblem(this.selectedProblem);
  }

  private flipSquare(index: number): void {
    if (index < 0 || index > this.grid.length - 1) {
      return;
    }

    this.grid[index] = (this.grid[index] + 1) % 2;
  }
}
