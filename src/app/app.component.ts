import { Component, ElementRef, Renderer2 } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  protected background: boolean = true;
  protected check: boolean = false;

  constructor(private renderer: Renderer2, private element: ElementRef) {}

  ngOnInit(): void {
    const check: NodeListOf<HTMLElement> =
      this.element.nativeElement.querySelectorAll('.content');

    const circle: NodeListOf<HTMLElement> =
      this.element.nativeElement.querySelectorAll('.circle');

    circle.forEach((value, index) => {
      value.addEventListener('click', () => {
        value.classList.toggle('image'), check[index].classList.toggle('check');
      });
    });

    this.getBtn().forEach((value) => {
      value.addEventListener('click', () => {
        this.remove();
        value.classList.add('select');
      });
    });
  }

  toggle(): void {
    const body: Element | null = document.querySelector('.body');
    this.background = !this.background;

    if (body?.classList.contains('dark')) {
      this.renderer.removeClass(body, 'dark');
    } else {
      this.renderer.addClass(body, 'dark');
    }
  }

  remove(): void {
    this.getBtn().forEach((value) => {
      value.classList.remove('select');
    });
  }

  getBtn(): NodeListOf<HTMLElement> {
    return this.element.nativeElement.querySelectorAll('.btn');
  }
}
