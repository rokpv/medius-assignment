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

  onSquareClicked(squareIndex: number): void {
    this.squareClicked.emit(squareIndex);
  }
}
