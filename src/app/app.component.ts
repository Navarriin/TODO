import { Observable } from 'rxjs';
import { Component, ElementRef, Renderer2 } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { TodoService } from './services/todo.service';
import { Todos } from './interface/Todos.interface';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  protected allTodos: Todos[] = [];
  protected content: string = '';

  protected background: boolean = true;
  protected check: boolean = false;

  constructor(
    private renderer: Renderer2,
    private element: ElementRef,
    private api: TodoService
  ) {}

  ngOnInit(): void {
    this.getAll();
  }

  getAll(): void {
    this.api.getAllTodos().subscribe((data) => {
      this.allTodos = data;
    });
  }

  postTodo(): void {
    if (!this.content) {
      return;
    }
    this.api
      .postTodo({
        content: this.content,
        disable: false,
      })
      .subscribe(() => {
        this.getAll();
        this.content = '';
      });
  }

  updateTodo(body: Todos): void {
    this.api
      .updateTodo({
        id: body.id,
        content: body.content,
        disable: !body.disable,
      })
      .subscribe(() => {
        this.getAll();
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

  addSelect(): void {
    this.getBtn().forEach((value) => value.classList.add('select'));
  }

  removeSelect(): void {
    this.getBtn().forEach((value) => value.classList.remove('select'));
  }

  getBtn(): NodeListOf<HTMLElement> {
    return this.element.nativeElement.querySelectorAll('.btn');
  }

  captalize(content: string): string {
    return content.charAt(0).toUpperCase() + content.slice(1);
  }
}
