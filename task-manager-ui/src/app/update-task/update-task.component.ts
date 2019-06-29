import {ChangeDetectorRef, Component, Inject, LOCALE_ID, OnInit} from '@angular/core';
import {Task} from '../model/Task';
import {ActivatedRoute, Router} from '@angular/router';
import {log} from 'util';
import {TaskService} from '../services/task.service';
import {DatePipe} from '@angular/common';
import {NgbModal, NgbModalConfig} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-update-task',
  templateUrl: './update-task.component.html',
  styleUrls: ['./update-task.component.css']
})
export class UpdateTaskComponent implements OnInit {

 
  task: Task;
  tasks: Task[];
  parentTask: Task;
  parentTitle = '';
  parentId  = '';

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
  }

  ngOnInit() {

    this.getAllTask();

    this.taskService.getTask(this.route.snapshot.paramMap.get('id')).subscribe(
      v => {
        this.task = v;
        this.parentTask = this.task.parentTask;
        this.parentTitle = this.parentTask && this.parentTask.title ? this.parentTask.title : '' ;
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

  onSubmit() {
    this.taskService.update(this.task)
      .subscribe(
       value => { this.router.navigate(['./viewTask']);
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
