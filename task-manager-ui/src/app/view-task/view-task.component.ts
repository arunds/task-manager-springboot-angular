import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Task} from '../model/Task';
import {ActivatedRoute, Router} from '@angular/router';
import {TaskService} from '../services/task.service';

@Component({
  selector: 'app-view-task',
  templateUrl: './view-task.component.html',
  styleUrls: ['./view-task.component.css']
})
export class ViewTaskComponent implements OnInit {
  tasks: Task[];
  filterTask: Task;
  min = 0;
  max = 5;

  constructor(
    private ref: ChangeDetectorRef,
    private route: ActivatedRoute,
    private router: Router,
    private taskService: TaskService) {
      this.getAllTask();
  }


  ngOnInit() {
    this.getAllTask();
    this.filterTask = new Task();
  }

  getAllTask()  {
    this.taskService.get().subscribe( v => {
        console.log(this.tasks);
        this.tasks = v;
        this.ref.detectChanges();
      }
    );
  }


  update(t: Task ): void {
    console.log('update task',t);
    this.router.navigate(['/updateTask' , t.id]);
  }

  delete(t: Task): void {
    this.taskService.deleteTask(t.id).subscribe(
      v => {
        this.getAllTask();
      }
    );

  }

}
