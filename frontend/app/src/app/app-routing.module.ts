import { NgModule } from '@angular/core';
import { RouterModule, Routes as RoutesType } from '@angular/router';

export enum Routes {
  Solve = 'solve',
  Create = 'create',
}

const routes: RoutesType = [
  { path: Routes.Solve, loadChildren: () => import('./modules/solve/solve.module').then(m => m.SolveModule) },
  { path: Routes.Create, loadChildren: () => import('./modules/create/create.module').then(m => m.CreateModule) },
  { path: '*', redirectTo: 'solve' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
