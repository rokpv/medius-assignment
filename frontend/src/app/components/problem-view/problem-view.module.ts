import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ProblemViewComponent} from './problem-view.component';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatCardModule} from '@angular/material/card';


@NgModule({
  declarations: [ProblemViewComponent],
  imports: [
    CommonModule,
    MatGridListModule,
    MatCardModule,
  ],
  exports: [
    ProblemViewComponent
  ]
})
export class ProblemViewModule { }
