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
  protected background: boolean = true;
  protected check: boolean = false;

  allTodos$ = new Observable<Todos[]>();
  content: string = '';

  constructor(
    private renderer: Renderer2,
    private element: ElementRef,
    private api: TodoService
  ) {}

  ngOnInit(): void {
    // this.interactions();
    this.getAll();
  }

  // interactions(): void {
  //   const check: NodeListOf<HTMLElement> =
  //     this.element.nativeElement.querySelectorAll('.content');

  //   const circle: NodeListOf<HTMLElement> =
  //     this.element.nativeElement.querySelectorAll('.circle');

  //   circle.forEach((value, index) => {
  //     value.addEventListener('click', () => {
  //       value.classList.toggle('image'), check[index].classList.toggle('check');
  //     });
  //   });

  //   this.getBtn().forEach((value) => {
  //     value.addEventListener('click', () => {
  //       this.remove();
  //       value.classList.add('select');
  //     });
  //   });
  // }

  getAll(): void {
    this.allTodos$ = this.api.getAllTodos();
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

  remove(): void {
    this.getBtn().forEach((value) => {
      value.classList.remove('select');
    });
  }

  getBtn(): NodeListOf<HTMLElement> {
    return this.element.nativeElement.querySelectorAll('.btn');
  }
}
