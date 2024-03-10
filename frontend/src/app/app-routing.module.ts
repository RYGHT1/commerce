import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { ProductFormComponent } from './components/product-form/product-form.component';

const routes: Routes = [
    { path: '', component: AppComponent },
    { path: 'new-product', component: ProductFormComponent },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {}
