import { Component } from '@angular/core';
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
  protected allTodos!: Todos[];
  protected allTodosFilter!: Todos[];
  protected numberDelete!: Todos[];

  protected content: string = '';
  protected selectedOption: string = 'all';

  protected background: boolean = true;
  protected check: boolean = false;

  constructor(private api: TodoService) {}

  ngOnInit(): void {
    this.getAll();
  }

  getAll(): void {
    this.api.getAllTodos().subscribe((data) => {
      this.allTodos = data;
      this.allTodosFilter = this.allTodos;
    });
  }

  postTodo(): void {
    if (!this.content) {
      return;
    }
    this.api.postTodo({ content: this.content }).subscribe(() => {
      this.getAll();
      this.content = '';
    });
  }

  updateTodo(body: Todos): void {
    // this.api
    //   .updateTodo({ id: body.id, content: body.content, status: !body.status })
    //   .subscribe(() => this.getAll());
  }

  deleteTodo(): void {
    this.numberDelete = this.allTodos.filter((value) => value.active === false);
    const idsToDelete: (number | undefined)[] = this.numberDelete.map(
      (todo) => todo.id
    );
    idsToDelete.forEach((id) => {
      this.api.deleteTodo(id!).subscribe(() => this.getAll());
    });
  }

  toggle(): void {
    const body: Element | null = document.querySelector('.body');
    this.background = !this.background;

    body?.classList.toggle('dark');
  }

  all() {
    //   this.allTodosFilter = this.allTodos;
    //   this.selectedOption = 'all';
  }

  active() {
    this.allTodosFilter = this.allTodos.filter(
      (value) => value.status === true
    );
    this.selectedOption = 'active';
  }

  completed() {
    //   this.allTodosFilter = this.allTodos.filter(
    //     (value) => value.disable === true
    //   );
    //   this.selectedOption = 'completed';
  }
}
