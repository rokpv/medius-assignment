import { Component } from '@angular/core';
import {Routes} from './app-routing.module';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  readonly links: string[] = [
    Routes.Solve,
    Routes.Create,
  ];
  activeLink = this.links[0];
}
