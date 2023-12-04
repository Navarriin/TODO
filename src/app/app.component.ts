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

  constructor(private renderer: Renderer2) {}

  toggle(): void {
    const body: Element | null = document.querySelector('.body');
    this.background = !this.background;

    if (body?.classList.contains('dark')) {
      this.renderer.removeClass(body, 'dark');
    } else {
      this.renderer.addClass(body, 'dark');
    }
  }

  clickCheck(): void {
    const check: HTMLElement | null = document.querySelector('.content');
    this.check = !this.check;

    if (check?.classList.contains('check')) {
      this.renderer.removeClass(check, 'check');
    } else {
      this, this.renderer.addClass(check, 'check');
    }
  }
}
