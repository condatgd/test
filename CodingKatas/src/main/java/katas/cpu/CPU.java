package katas.cpu;

import lombok.ToString;

import java.util.List;

@ToString
public class CPU {
    private final List<String> program;
    private int pc;
    private int adr;
    private String instr;
    private int data;
    private int a;
    private int b;
    private int c;

    private boolean a_w = false;
    private boolean b_w = false;
    private boolean c_w = false;
    private boolean pc_w = false;

    public CPU(List<String> program) {
        this.program = program;
        this.pc = 0;
    }

    public static void main(String[] args) {
        CPU cpu = new CPU(List.of("LDA 1", "LDB 1", "ADD", "JMP 1"));
        for(int i=0; i<5; i++) {
            System.out.println(cpu.program.get(cpu.pc));
            cpu.fetch();            System.out.println("fetch  : " + cpu);
            cpu.decode();           System.out.println("decode : " + cpu);
            cpu.execute();          System.out.println("execute: " + cpu);
            cpu.store();            System.out.println("store  : " + cpu);
        }
    }

    private void fetch() {
        adr = pc;
        instr = data();
    }

    private void decode() {
        a_w = false;
        b_w = false;
        c_w = false;
        pc_w = false;
        if(instr.startsWith("LDA")) {
            String[] split = instr.split(" ");
            data = Integer.parseInt(split[1]);
            a_w = true;
        }
        if(instr.startsWith("LDB")) {
            String[] split = instr.split(" ");
            data = Integer.parseInt(split[1]);
            b_w = true;
        }
        if(instr.startsWith("ADD")) {
            c_w = true;
        }
        if(instr.startsWith("JMP")) {
            String[] split = instr.split(" ");
            data = Integer.parseInt(split[1]);
            pc_w = true;
        }
    }

    private void execute() {
        if(a_w) a = data;
        if(b_w) b = data;
        if(c_w) c = a+b;
        if(pc_w) pc = data;
    }

    private void store() {
        if(!pc_w) {
            pc++;
        }
    }

    private String data() {
        return program.get(adr);
    }
}
