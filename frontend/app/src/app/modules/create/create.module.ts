import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CreatePageComponent} from './create-page/create-page.component';
import {CreateRoutingModule} from './create-routing.module';


@NgModule({
  declarations: [
    CreatePageComponent
  ],
  imports: [
    CommonModule,
    CreateRoutingModule,
  ],
  entryComponents: [
    CreatePageComponent
  ]
})
export class CreateModule { }
