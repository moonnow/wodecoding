import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ColumnsListComponent } from './columns-list/columns-list.component';
import { ColumnsEditComponent } from './columns-edit/columns-edit.component';
import { ColumnsDetailComponent } from './columns-detail/columns-detail.component';

const routes: Routes = [
  { path: 'list', component: ColumnsListComponent },
  { path: 'edit', component: ColumnsEditComponent },
  { path: 'edit/:columnsId', component: ColumnsEditComponent, data: { title: '编辑列' } },
  { path: 'detail/:columnsId', component: ColumnsDetailComponent, data: { title: '列详情' } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ColumnsRoutingModule { }
