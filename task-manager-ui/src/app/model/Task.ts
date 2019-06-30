export class Task {
   id: string;
   title: string;
   priority: number;
   startDate: Date;
   endDate: Date;
   summary: string;
   parentTask: Task;
   status: string;
}
