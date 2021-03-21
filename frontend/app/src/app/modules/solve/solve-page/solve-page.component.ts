import { Component, OnInit } from '@angular/core';
import {ApiService} from '../../../services/api.service';
import {Problem} from '../../../models/Problem';

@Component({
  templateUrl: './solve-page.component.html',
  styleUrls: ['./solve-page.component.scss']
})
export class SolvePageComponent implements OnInit {

  problems: Problem[] = [];
  selectedProblem: Problem | null = null;

  grid: number[] = [];

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
  }

  onSquareClicked(index: number): void {
    if (!this.selectedProblem) {
      console.warn('Selected problem is null.');
      return;
    }
    const dim = this.selectedProblem.size;

    this.flipSquare(index);
    this.flipSquare(index - 1);
    this.flipSquare(index + 1);
    this.flipSquare(index - dim);
    this.flipSquare(index + dim);
  }

  /**
   * Transforms a problem's description string into an array.
   *
   * "1010" => [1, 0, 1, 0]
   */
  parseGrid(problem: Problem): number[] {
    return problem.description.split('').map(s => parseInt(s, 10));
  }

  private flipSquare(index: number): void {
    if (index < 0 || index > this.grid.length - 1) {
      return;
    }

    this.grid[index] = (this.grid[index] + 1) % 2;
  }
}
