import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { SortListComponent } from './sort-list/sort-list.component';
import { SortEditComponent } from './sort-edit/sort-edit.component';
import { SortDetailComponent } from './sort-detail/sort-detail.component';

const routes: Routes = [
  { path: 'list', component: SortListComponent },
  { path: 'edit', component: SortEditComponent },
  { path: 'edit/:sortId', component: SortEditComponent, data: { title: '编辑排序' } },
  { path: 'detail/:sortId', component: SortDetailComponent, data: { title: '排序详情' } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SortRoutingModule { }
