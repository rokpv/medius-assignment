import { Component, OnInit } from '@angular/core';
import {ApiService} from '../../../services/api.service';

@Component({
  selector: 'app-create-page',
  templateUrl: './create-page.component.html',
  styleUrls: ['./create-page.component.scss']
})
export class CreatePageComponent implements OnInit {

  dimension = 3;
  grid: number[] = [];

  /**
   * Server response message
   */
  message = '';

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.resetGrid();
  }

  onSquareClicked(index: number): void {
    this.flipSquare(index);
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
    this.message = '';
  }

  private flipSquare(index: number): void {
    if (index < 0 || index > this.grid.length - 1) {
      return;
    }

    this.grid[index] = (this.grid[index] + 1) % 2;
  }

  submitProblem(): void {
    this.apiService.createProblem(this.dimension, this.stringifyGrid()).subscribe(
      success => this.showMessage('Problem saved successfully.'),
      error => this.showMessage('Problem has no solution. Please try a different problem.')
    );
  }

  /**
   * Displays a message for 5 sec.
   */
  private showMessage(msg: string): void {
    this.message = msg;
    setTimeout(() => this.message = '', 5000);
  }

  /**
   * Transforms an array of numbers into a string of those numbers.
   *
   * [1, 0, 1, 0] => "1010"
   */
  private stringifyGrid(): string {
    return this.grid.toString().replace(/\D/g, '');
  }
}
