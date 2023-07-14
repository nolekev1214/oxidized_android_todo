use j4rs::jni_sys::jint;
use j4rs::jni_sys::JavaVM;
use j4rs::prelude::*;

use crossbeam::channel::Receiver;
use crossbeam::channel::Sender;
use j4rs_derive::call_from_java;
use lazy_static::lazy_static;

const JNI_VERSION_1_6: jint = 0x00010006;

#[allow(non_snake_case)]
#[no_mangle]
pub extern "C" fn jni_onload(env: *mut JavaVM, _reserved: jobject) -> jint {
    j4rs::set_java_vm(env);
    JNI_VERSION_1_6
}

type InternalDatabase = Vec<TODOItem>;

struct TODOItem {
    pub todo: String,
    pub categroy: String,
    pub completition_status: bool,
}

enum Request {
    Add(TODOItem),
    GetAll(Sender<InternalDatabase>),
}

lazy_static! {
    static ref CHANNEL: (Sender<Request>, Receiver<Request>) = crossbeam::channel::bounded(10);
}

#[call_from_java("com.example.todolist.RustDatabase.startDatabase")]
fn start_database() {
    std::thread::spawn(channel_monitor);
}

fn channel_monitor() {
    while let Ok(request) = CHANNEL.1.recv() {
        match request {
            Request::Add(_item) => {}
            Request::GetAll(_tx) => {}
        }
    }
}
