import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl, FormArray, AbstractControl } from '@angular/forms';

@Component({
    selector: 'app-product-form',
    templateUrl: './product-form.component.html',
})
export class ProductFormComponent implements OnInit {
    productForm: FormGroup;

    constructor(private formBuilder: FormBuilder) {
        this.productForm = this.renderForm();
    }

    ngOnInit(): void { }

    renderForm() {
        return this.formBuilder.group({
            name: [
                '',
                [
                    Validators.required,
                    Validators.maxLength(100),
                    Validators.minLength(3),
                ],
            ],
            description: ['', [Validators.required]],
            SKU: ['', [Validators.required]],
            categoryName: ['', [Validators.required]],
            price: ['', [Validators.required]],
            inventoryCount: [0, [Validators.required, Validators.min(0)]],
            imageUrl: [''],
        });
    }

    onSubmit() {
        throw new Error('Method not implemented.');
    }
}
