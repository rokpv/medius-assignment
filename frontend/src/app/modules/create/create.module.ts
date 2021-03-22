import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CreatePageComponent} from './create-page/create-page.component';
import {CreateRoutingModule} from './create-routing.module';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {ProblemViewModule} from '../../components/problem-view/problem-view.module';


@NgModule({
  declarations: [
    CreatePageComponent
  ],
  imports: [
    CommonModule,
    CreateRoutingModule,
    MatGridListModule,
    MatButtonModule,
    MatInputModule,
    ProblemViewModule,
  ],
  entryComponents: [
    CreatePageComponent
  ]
})
export class CreateModule { }
