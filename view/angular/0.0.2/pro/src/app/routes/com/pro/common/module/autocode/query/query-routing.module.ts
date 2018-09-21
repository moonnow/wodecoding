import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { QueryListComponent } from './query-list/query-list.component';
import { QueryEditComponent } from './query-edit/query-edit.component';
import { QueryDetailComponent } from './query-detail/query-detail.component';

const routes: Routes = [
  { path: 'list', component: QueryListComponent },
  { path: 'edit', component: QueryEditComponent },
  { path: 'edit/:queryId', component: QueryEditComponent, data: { title: '编辑查询' } },
  { path: 'detail/:queryId', component: QueryDetailComponent, data: { title: '查询详情' } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class QueryRoutingModule { }
