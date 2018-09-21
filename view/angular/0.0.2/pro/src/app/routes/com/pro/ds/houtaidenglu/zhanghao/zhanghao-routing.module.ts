import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ZhanghaoListComponent } from './zhanghao-list/zhanghao-list.component';
import { ZhanghaoEditComponent } from './zhanghao-edit/zhanghao-edit.component';
import { ZhanghaoDetailComponent } from './zhanghao-detail/zhanghao-detail.component';

const routes: Routes = [
  { path: 'list', component: ZhanghaoListComponent },
  { path: 'edit', component: ZhanghaoEditComponent },
  { path: 'edit/:zhanghaoId', component: ZhanghaoEditComponent, data: { title: '编辑账号' } },
  { path: 'detail/:zhanghaoId', component: ZhanghaoDetailComponent, data: { title: '账号详情' } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ZhanghaoRoutingModule { }
