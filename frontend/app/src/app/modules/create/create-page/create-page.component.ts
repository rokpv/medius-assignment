import { Component, OnInit } from '@angular/core';
import {Problem} from '../../../models/Problem';
import {Solution} from '../../../models/Solution';
import {ApiService} from '../../../services/api.service';

@Component({
  selector: 'app-create-page',
  templateUrl: './create-page.component.html',
  styleUrls: ['./create-page.component.scss']
})
export class CreatePageComponent implements OnInit {

  dimension = 3;
  grid: number[] = [];

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.resetGrid();
  }

  onSquareClicked(index: number): void {
    this.flipSquare(index);
  }

  /**
   * Transforms an array of numbers into a string of those numbers.
   *
   * [1, 0, 1, 0] => "1010"
   */
  stringifyGrid(grid: number[]): string {
    return grid.toString().replace(',', '');
  }

  onDimensionChanged(e: any): void {
    if (!e.data) {
      console.warn('Unexpected event type', e);
    }

    this.dimension = parseInt(e.data, 10);
    this.resetGrid();
  }

  resetGrid(): void {
    this.grid = new Array(this.dimension * this.dimension).fill(0);
  }

  private flipSquare(index: number): void {
    if (index < 0 || index > this.grid.length - 1) {
      return;
    }

    this.grid[index] = (this.grid[index] + 1) % 2;
  }

  submitProblem() {

  }
}
