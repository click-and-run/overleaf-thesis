import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { DragndropDirective } from './dragndrop.directive';

@Component({
    selector: 'jhi-dragndrop',
    templateUrl: './dragndrop.component.html',
    styleUrls: ['./dragndrop.component.css']
})
export class DragndropComponent implements OnInit {

    @Input() public fileLimit: number;

    files: Array<File> = new Array<File>();
    @Output() private filesChange: EventEmitter<Array<File>> = new EventEmitter();
    @ViewChild(DragndropDirective) dragndropDirective: DragndropDirective;

    constructor() {
    }

    ngOnInit() {
    }

    public clearFiles() {
        this.dragndropDirective.clearFiles();
    }

    public onFilesChange(files) {
        this.filesChange.emit(files);
    }
}
