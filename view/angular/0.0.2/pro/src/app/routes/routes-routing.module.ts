import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { environment } from '@env/environment';

import { LayoutDefaultComponent } from '../layout/default/default.component';
import { LayoutFullScreenComponent } from '../layout/fullscreen/fullscreen.component';
import { LayoutPassportComponent } from '../layout/passport/passport.component';

import { UserLoginComponent } from './passport/login/login.component';
import { UserRegisterComponent } from './passport/register/register.component';
import { UserRegisterResultComponent } from './passport/register-result/register-result.component';

import { SystemHomepageComponent } from './com/pro/common/module/homepage/system-homepage/system-homepage.component';
import { PersonalHomepageComponent } from './com/pro/common/module/homepage/personal-homepage/personal-homepage.component';
import { CallbackComponent } from './callback/callback.component';
import { Exception403Component } from './exception/403.component';
import { Exception404Component } from './exception/404.component';
import { Exception500Component } from './exception/500.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutPassportComponent,
    children: [
      { path: '', redirectTo: 'login', pathMatch: 'full' },
      { path: 'login', component: UserLoginComponent, data: { title: '登录', titleI18n: 'pro-login' } },
      { path: 'register', component: UserRegisterComponent, data: { title: '注册', titleI18n: 'pro-register' } },
      { path: 'register-result', component: UserRegisterResultComponent, data: { title: '注册结果', titleI18n: 'pro-register-result' } },
    ],
  },
  {
    path: 'pro',
    component: LayoutDefaultComponent,
    children: [
      // { path: '', redirectTo: 'homepage/system_homepage', pathMatch: 'full' },
      // { path: 'homepage', redirectTo: 'homepage/system_homepage', pathMatch: 'full' },
      { path: 'homepage/system_homepage', component: SystemHomepageComponent },
      { path: 'homepage/personal_homepage', component: PersonalHomepageComponent },
      { path: 'code/plugin/dt', loadChildren: './com/pro/code/plugin/dt/dt.module#DtModule' },
      { path: 'code/plugin/columns', loadChildren: './com/pro/code/plugin/columns/columns.module#ColumnsModule' },
      { path: 'code/plugin/sort', loadChildren: './com/pro/code/plugin/sort/sort.module#SortModule' },
      { path: 'code/plugin/query', loadChildren: './com/pro/code/plugin/query/query.module#QueryModule' },
      { path: 'code/plugin/coding', loadChildren: './com/pro/code/plugin/coding/coding.module#CodingModule' },
      // { path: 'pro/common/module/account/user', loadChildren: './com/pro/common/module/account/user/user.module#UserModule' },
      // { path: 'pro/common/module/account/info', loadChildren: './com/pro/common/module/account/info/info.module#InfoModule' },
      // { path: 'pro/common/module/account/account', loadChildren: './com/pro/common/module/account/account/account.module#AccountModule' },
      // { path: 'pro/common/module/account/group', loadChildren: './com/pro/common/module/account/group/group.module#GroupModule' },
      // { path: 'pro/ds/houtaidenglu/zhanghao', loadChildren: './com/pro/ds/houtaidenglu/zhanghao/zhanghao.module#ZhanghaoModule' },
      // { path: 'pro/ds/houtaidenglu/huihua', loadChildren: './com/pro/ds/houtaidenglu/huihua/huihua.module#HuihuaModule' },
      // { path: 'pro/ds/houtaidenglu/denglurizhi', loadChildren: './com/pro/ds/houtaidenglu/denglurizhi/denglurizhi.module#DenglurizhiModule' },
    ],
  },
  { path: 'callback/:type', component: CallbackComponent },
  { path: '403', component: Exception403Component },
  { path: '404', component: Exception404Component },
  { path: '500', component: Exception500Component },
  { path: '**', redirectTo: '404' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: environment.useHash })],
  exports: [RouterModule],
})
export class RouteRoutingModule {}
