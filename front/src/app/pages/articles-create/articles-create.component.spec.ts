import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticlesCreateComponent } from './articles-create.component';

describe('ArticlesCreateComponent', () => {
  let component: ArticlesCreateComponent;
  let fixture: ComponentFixture<ArticlesCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArticlesCreateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArticlesCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
