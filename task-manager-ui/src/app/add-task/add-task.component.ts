import {ChangeDetectorRef, Component, Inject, LOCALE_ID, OnInit, Output} from '@angular/core';
import {log} from 'util';
import {ActivatedRoute, Router} from '@angular/router';
import {Task} from '../model/Task';
import {DatePipe} from '@angular/common';
import {NgbModal, NgbModalConfig} from '@ng-bootstrap/ng-bootstrap';
import {TaskService} from '../services/task.service';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit {

 
  task: Task;  
  parentTask: Task;
  parentTitle = '';
  parentId  = '';
  tasks: Task[];

  constructor(
    private ref: ChangeDetectorRef,
    private route: ActivatedRoute,
    private router: Router,
    private taskService: TaskService,
    @Inject(LOCALE_ID) private locale: string,
    private config: NgbModalConfig,
    private modalService: NgbModal
  ) {
    // customize default values of modals used by this component tree
    config.backdrop = 'static';
    config.keyboard = false;
    this.parentId = '';
    this.task = new Task();
    this.task.priority = 1;
    this.task.title = '';
    this.task.parentTask = new Task();
    this.task.parentTask.title = '';


  }


  ngOnInit() {
    this.getAllTask();
  }
  onSubmit() {
    this.taskService.addTask(this.task).then(
      value => {
        this.router.navigate(['./viewTask']);
      }
        );
      }


  getAllTask()  {
    this.taskService.get().subscribe( v => {
        console.log(this.tasks);
        this.tasks = v;
        this.ref.detectChanges();
      }
    );
  }



  openParentTask(content) {
    this.taskService.get().subscribe( v => {
        this.tasks = v;
        this.modalService.open(content);
        this.ref.detectChanges();
      }
    );
  }

  saveParentTask() {
    this.task.parentTask = this.tasks
      .filter(u => { if (u.id === this.parentId) { return u; } }).map(u => u)[0];
    if (this.modalService.hasOpenModals()) { this.modalService.dismissAll(); }
    this.parentTitle = this.task.parentTask.title;
    return true;
  }
}
