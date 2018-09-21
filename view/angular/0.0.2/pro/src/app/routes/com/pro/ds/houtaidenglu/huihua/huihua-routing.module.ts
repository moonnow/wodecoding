import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HuihuaListComponent } from './huihua-list/huihua-list.component';
import { HuihuaEditComponent } from './huihua-edit/huihua-edit.component';
import { HuihuaDetailComponent } from './huihua-detail/huihua-detail.component';

const routes: Routes = [
  { path: 'list', component: HuihuaListComponent },
  { path: 'edit', component: HuihuaEditComponent },
  { path: 'edit/:huihuaId', component: HuihuaEditComponent, data: { title: '编辑会话' } },
  { path: 'detail/:huihuaId', component: HuihuaDetailComponent, data: { title: '会话详情' } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HuihuaRoutingModule { }
