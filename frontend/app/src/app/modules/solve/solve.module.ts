import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SolvePageComponent} from './solve-page/solve-page.component';
import {SolveRoutingModule} from './solve-routing.module';


@NgModule({
  declarations: [
    SolvePageComponent
  ],
  imports: [
    SolveRoutingModule,
    CommonModule
  ],
  entryComponents: [
    SolvePageComponent
  ]
})
export class SolveModule { }
