import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UserListComponent } from './user-list/user-list.component';
import { UserEditComponent } from './user-edit/user-edit.component';
import { UserDetailComponent } from './user-detail/user-detail.component';

const routes: Routes = [
  { path: 'list', component: UserListComponent },
  { path: 'edit', component: UserEditComponent },
  { path: 'edit/:userId', component: UserEditComponent, data: { title: '编辑用户' } },
  { path: 'detail/:userId', component: UserDetailComponent, data: { title: '用户详情' } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
