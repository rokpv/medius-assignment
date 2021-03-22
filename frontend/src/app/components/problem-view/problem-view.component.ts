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
  set solution(x: Solution | null) {
    this.mSolution = x;
    this.resetSolution();
  }

  solutionIndices?: number[];
  clickedSolutions: number[] = [];

  private mSolution: Solution | null = null;

  onSquareClicked(squareIndex: number): void {
    this.squareClicked.emit(squareIndex);
  }

  /**
   * Checks if index i is in the solution indices array
   */
  isSolution(i: number): boolean {
    if (!this.mSolution) {
      return false;
    }
    // cache indices for reuse
    if (!this.solutionIndices) {
      this.solutionIndices = this.mSolution.steps.map(s => s.index);
    }
    return this.solutionIndices.includes(i);
  }

  resetSolution(): void {
    this.clickedSolutions = [];
    this.solutionIndices = undefined;
  }
}
