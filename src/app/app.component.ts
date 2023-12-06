import { Component, ElementRef } from '@angular/core';
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
  protected allTodosLength: Todos[] = [];
  protected allTodosFilter: Todos[] = [];
  protected content: string = '';
  selectedOption: string = 'all';

  protected background: boolean = true;
  protected check: boolean = false;

  constructor(private element: ElementRef, private api: TodoService) {}

  ngOnInit(): void {
    const btn: NodeListOf<Element> = document.querySelectorAll('.btn');
    this.getAll();

    btn.forEach;
  }

  getAll(): void {
    this.api.getAllTodos().subscribe((data) => {
      this.allTodos = data;
      this.allTodosFilter = this.allTodos;
      this.allTodosLength = data.filter((value) => value.disable === false);
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

    body?.classList.toggle('dark');
  }

  all() {
    this.api.getAllTodos().subscribe((data) => {
      this.allTodosFilter = data;
      this.selectedOption = 'all';
    });
  }
  active() {
    this.api.getAllTodos().subscribe((data) => {
      this.allTodosFilter = data.filter((value) => value.disable === false);
      this.selectedOption = 'active';
    });
  }
  completed() {
    this.api.getAllTodos().subscribe((data) => {
      this.allTodosFilter = data.filter((value) => value.disable === true);
      this.selectedOption = 'completed';
    });
  }
}
