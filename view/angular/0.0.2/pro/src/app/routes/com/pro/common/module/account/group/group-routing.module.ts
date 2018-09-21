import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { GroupListComponent } from './group-list/group-list.component';
import { GroupEditComponent } from './group-edit/group-edit.component';
import { GroupDetailComponent } from './group-detail/group-detail.component';

const routes: Routes = [
  { path: 'list', component: GroupListComponent },
  { path: 'edit', component: GroupEditComponent },
  { path: 'edit/:groupId', component: GroupEditComponent, data: { title: '编辑组' } },
  { path: 'detail/:groupId', component: GroupDetailComponent, data: { title: '组详情' } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GroupRoutingModule { }
