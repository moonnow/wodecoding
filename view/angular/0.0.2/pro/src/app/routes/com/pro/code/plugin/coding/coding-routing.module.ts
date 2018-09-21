import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CodingViewComponent } from './coding-view/coding-view.component';

const routes: Routes = [
  { path: 'view', component: CodingViewComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CodingRoutingModule { }
