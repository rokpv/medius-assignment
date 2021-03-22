import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Solution} from '../../models/Solution';

@Component({
  selector: 'app-problem-view',
  templateUrl: './problem-view.component.html',
  styleUrls: ['./problem-view.component.scss']
})
export class ProblemViewComponent {

  @Output()
  readonly squareClicked = new EventEmitter<number>();

  @Input()
  size = '200px';

  @Input()
  dimension = 3;

  @Input()
  grid: number[] = [];

  @Input()
  squareColor = '#999999';

  @Input()
  solution: Solution | null = null;

  solutionIndices?: number[];
  clickedSolutions: number[] = [];

  onSquareClicked(squareIndex: number): void {
    this.squareClicked.emit(squareIndex);
  }

  isSolution(i: number): boolean {
    if (!this.solution) {
      return false;
    }

    if (!this.solutionIndices) {
      this.solutionIndices = this.solution.steps.map(s => s.index);
    }

    console.log(this.solutionIndices.includes(i));
    return this.solutionIndices.includes(i);
  }

  resetSolution(): void {
    this.clickedSolutions = [];
  }
}
