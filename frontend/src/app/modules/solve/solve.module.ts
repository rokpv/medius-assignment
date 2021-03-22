import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SolvePageComponent} from './solve-page/solve-page.component';
import {SolveRoutingModule} from './solve-routing.module';
import {ProblemViewModule} from '../../components/problem-view/problem-view.module';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatButtonModule} from '@angular/material/button';


@NgModule({
  declarations: [
    SolvePageComponent
  ],
  imports: [
    SolveRoutingModule,
    CommonModule,
    ProblemViewModule,
    MatGridListModule,
    MatButtonModule,
  ],
  entryComponents: [
    SolvePageComponent
  ]
})
export class SolveModule { }
