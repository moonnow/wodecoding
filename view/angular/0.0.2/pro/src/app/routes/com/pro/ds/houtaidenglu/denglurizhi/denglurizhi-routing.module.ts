import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DenglurizhiListComponent } from './denglurizhi-list/denglurizhi-list.component';
import { DenglurizhiEditComponent } from './denglurizhi-edit/denglurizhi-edit.component';
import { DenglurizhiDetailComponent } from './denglurizhi-detail/denglurizhi-detail.component';

const routes: Routes = [
  { path: 'list', component: DenglurizhiListComponent },
  { path: 'edit', component: DenglurizhiEditComponent },
  { path: 'edit/:denglurizhiId', component: DenglurizhiEditComponent, data: { title: '编辑登录日志' } },
  { path: 'detail/:denglurizhiId', component: DenglurizhiDetailComponent, data: { title: '登录日志详情' } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DenglurizhiRoutingModule { }
