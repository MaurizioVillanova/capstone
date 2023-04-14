import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProdottospecificoComponent } from './prodottospecifico.component';

describe('ProdottospecificoComponent', () => {
  let component: ProdottospecificoComponent;
  let fixture: ComponentFixture<ProdottospecificoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProdottospecificoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProdottospecificoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
