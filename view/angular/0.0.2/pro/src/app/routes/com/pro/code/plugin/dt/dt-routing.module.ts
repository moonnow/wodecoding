import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DtListComponent } from './dt-list/dt-list.component';
import { DtEditComponent } from './dt-edit/dt-edit.component';
import { DtDetailComponent } from './dt-detail/dt-detail.component';

const routes: Routes = [
  { path: 'list', component: DtListComponent },
  { path: 'edit', component: DtEditComponent },
  { path: 'edit/:dtId', component: DtEditComponent, data: { title: '编辑数据库表' } },
  { path: 'detail/:dtId', component: DtDetailComponent, data: { title: '数据库表详情' } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DtRoutingModule { }
