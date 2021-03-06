import {Component, OnInit} from '@angular/core';
import {Routes} from './app-routing.module';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  readonly links: string[] = [
    Routes.Solve,
    Routes.Create,
  ];

  activeLink: string = Routes.Solve;

  ngOnInit(): void {
    // Fix for incorrect tab highlight during page refresh.
    const urlParts = window.location.href.split('/');
    this.activeLink = urlParts[urlParts.length - 1];
  }
}
