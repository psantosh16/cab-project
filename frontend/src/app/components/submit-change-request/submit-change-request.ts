import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
    selector: 'app-submit-change-request',
    imports: [ReactiveFormsModule, CommonModule, RouterModule],
    templateUrl: './submit-change-request.html'
})
export class SubmitChangeRequest {
    changeRequestForm: FormGroup;
    submitted = false;
    userName = 'John Doe';
    userEmail = 'john.doe@company.com';
    attachedFiles: File[] = [];

    constructor(private fb: FormBuilder) {
        this.changeRequestForm = this.fb.group({
            title: ['', [Validators.required, Validators.minLength(5)]],
            description: ['', [Validators.required, Validators.minLength(20)]],
            businessJustification: ['', [Validators.required, Validators.minLength(20)]],
            impact: ['', [Validators.required]],
            risk: ['', [Validators.required]],
            changeType: ['', [Validators.required]],
            proposedSchedule: ['', [Validators.required]],
            documentName: [''],
            documentFile: [null]
        });
    }

    get f() {
        return this.changeRequestForm.controls;
    }

    onFileSelected(event: any): void {
        const files = event.target.files;
        if (files && files.length > 0) {
            this.attachedFiles = Array.from(files);
            this.changeRequestForm.patchValue({
                documentFile: files[0]
            });
        }
    }

    removeFile(index: number): void {
        this.attachedFiles.splice(index, 1);
    }

    onSubmit(): void {
        this.submitted = true;
        if (this.changeRequestForm.invalid) {
            return;
        }

        // Handle form submission logic here
        console.log('Change request submitted', this.changeRequestForm.value);
        console.log('Attached files:', this.attachedFiles);
    }

    onLogout(): void {
        // Handle logout logic here
        console.log('Logout clicked');
    }
}
