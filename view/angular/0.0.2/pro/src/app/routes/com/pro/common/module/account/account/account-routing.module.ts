import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AccountListComponent } from './account-list/account-list.component';
import { AccountEditComponent } from './account-edit/account-edit.component';
import { AccountDetailComponent } from './account-detail/account-detail.component';

const routes: Routes = [
  { path: 'list', component: AccountListComponent },
  { path: 'edit', component: AccountEditComponent },
  { path: 'edit/:accountId', component: AccountEditComponent, data: { title: '编辑帐号' } },
  { path: 'detail/:accountId', component: AccountDetailComponent, data: { title: '帐号详情' } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccountRoutingModule { }
