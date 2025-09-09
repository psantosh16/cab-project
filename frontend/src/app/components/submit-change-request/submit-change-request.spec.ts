import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubmitChangeRequest } from './submit-change-request';

describe('SubmitChangeRequest', () => {
    let component: SubmitChangeRequest;
    let fixture: ComponentFixture<SubmitChangeRequest>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [SubmitChangeRequest]
        })
            .compileComponents();

        fixture = TestBed.createComponent(SubmitChangeRequest);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
