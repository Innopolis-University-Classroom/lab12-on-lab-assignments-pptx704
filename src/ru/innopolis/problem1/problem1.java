class Problem1 {
    interface Handler {
        void setNext(Handler h);
        void handle();
    }

    class BaseHandler implements Handler {
        Handler next = null;

        @Override
        public void setNext(Handler h) {
            this.next = h;
        }

        @Override
        public void handle() {
            if (next != null) next.handle();
        }
    }

    class FileHandler extends BaseHandler {
        @Override
        public void handle() {
            System.out.println("Written in file");
            super.handle();
        }
    }

    class ConsoleHandler extends BaseHandler {
        @Override
        public void handle() {
            System.out.println("Print on console.");
            super.handle();
        }
    }

    class BackendHandler extends BaseHandler {
        @Override
        public void handle() {
            System.out.println("Message sent to Server");
            super.handle();
        }
    }

    class LoggerFramework {
        Handler fileHandler = new FileHandler();
        Handler consoleHandler = new ConsoleHandler(); 
        Handler serverHandler = new BackendHandler();
        boolean writeOnlyMode = true;
        public LoggerFramework() {
            fileHandler.setNext(consoleHandler);
            consoleHandler.setNext(serverHandler);
        }

        public void setWriteToOnlyFiles() {
            if (writeOnlyMode) {
                fileHandler.setNext(null);
            } else {
                fileHandler.setNext(consoleHandler);
            }
            writeOnlyMode = !writeOnlyMode;
        }

        public boolean isWriteOnly(){
            return writeOnlyMode;
        }
        public void write() {
            fileHandler.handle();
        }
    }
}