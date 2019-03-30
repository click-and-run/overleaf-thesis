private void validateQuestionsSheet(ExellSheetView view, ExellSheetView viewConsignes) {

    this.log.info(" + Validating sheet [" + SHEET_NAME_QUESTIONS + "]");

    // Validate the content of the XLS
    Row row;
    int rowNumber, rowCount = view.getNRow();

    // Validate sequence
    String uuid;
    String type, instructionType;
    boolean uuidWellFormed = true;

    Counter<String> stats = new Counter<>();
    Counter<String> statsLg = new Counter<>();
    HashSet<String> uuids = new HashSet<>();

    //
    for (int i = 0; i < rowCount; i++) {
        // Get the actual row
        row = view.getRowAt(i);
        rowNumber = i + 2;

        // Check if we are still in the same context
        uuid = ("" + row.getValue(LevelTestExcelV2ColumnName.UUID)).trim();
        type = row.getValue(LevelTestExcelV2ColumnName.TYPE);

        /// Additional tests
        if (this.skip(SHEET_NAME_QUESTIONS, rowNumber, row)) {
            continue;
        }
        // end additional tests

        if (NEW_UUID.equals(uuid)) {
            uuid = UUID_FORMAT.format(new Date());
            view.setValue(i, LevelTestExcelV2ColumnName.UUID, uuid);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (StringUtils.isBlank(uuid)) {
            uuidWellFormed = false;
            this.reportError(SHEET_NAME_QUESTIONS, rowNumber, "Blank UUID, should have a valid UUID or NEW value");
        } else if (!UUID_DIGIT_FORMAT.matcher(uuid).matches()) {
            uuidWellFormed = false;
            this.reportError(SHEET_NAME_QUESTIONS, rowNumber, "UUID doesn't contain only digits");
        }

    // SKIPPED ABOUT 200 LINES OF CODE

    checkStats(statsLg, stats);
    ExellUtils.writeExellRE(xlsDoc, xls, xlsConfig);

}