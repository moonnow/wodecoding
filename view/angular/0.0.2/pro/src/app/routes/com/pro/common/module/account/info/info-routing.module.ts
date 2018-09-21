import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { InfoListComponent } from './info-list/info-list.component';
import { InfoEditComponent } from './info-edit/info-edit.component';
import { InfoDetailComponent } from './info-detail/info-detail.component';

const routes: Routes = [
  { path: 'list', component: InfoListComponent },
  { path: 'edit', component: InfoEditComponent },
  { path: 'edit/:infoId', component: InfoEditComponent, data: { title: '编辑用户信息' } },
  { path: 'detail/:infoId', component: InfoDetailComponent, data: { title: '用户信息详情' } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InfoRoutingModule { }
