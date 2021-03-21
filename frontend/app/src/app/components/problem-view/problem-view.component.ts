import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Problem} from '../../models/Problem';

@Component({
  selector: 'app-problem-view',
  templateUrl: './problem-view.component.html',
  styleUrls: ['./problem-view.component.scss']
})
export class ProblemViewComponent implements OnInit {

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

  ngOnInit(): void {
  }

  onSquareClicked(squareIndex: number): void {
    this.squareClicked.emit(squareIndex);
  }
}
