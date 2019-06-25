package com.example.generartransaccioncombustible.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class Const {

    ///  IP Servidor Web
    //public static String IP_SERVER = "www.assac.com.pe";//"192.168.1.21";

    public static String IP_SERVER = "192.168.1.26";//"192.168.1.21";

    //  Dirección Servicio Api
    //public static final String HTTP_SITE = "http://" + IP_SERVER + "/"; //ServicioWebApi;
    public static final String HTTP_SITE = "http://" + IP_SERVER + "/serviciowebapi/";
    public static final String HTTP_SITE_API = HTTP_SITE + "Api/";
    //public static final String HTTP_SITE_API = HTTP_SITE + "ServicioWebApi/Api/";

    //  Llave de acceso para ejecutar el servicio
    public static final String HTTP_SITE_KEY = "Ll4V3serv1ci0.Api";

    public static final String SERVICE_NAME = "Api/";
    //public static final String SERVICE_NAME = "/ServicioWebApi/Api/";

    public static final String REGEX_LETRAS_NUMEROS_GUION = "^[A-Za-z0-9-]*$";
    public static final String REGEX_LETRAS_NUMEROS = "^[A-Za-z0-9]*$";
    public static final String REGEX_DECIMLES = "^\\d+(\\.\\d{1,3})?$";

    public static final String STR_WAIT = "Espere";
    public static final String STR_ADVERTENCIA = "¡Advertencia!";
    public static final String STR_INFORMATION = "Información";
    public static final String STR_IMPORTANT = "¡Importante!";
    public static final String STR_QUESTION = "Pregunta";
    public static final String STR_CONFIRM = "Confirmar";
    public static final String STR_OK = "Ok";
    public static final String STR_SEND = "Enviar";
    public static final String STR_NO = "No";
    public static final String STR_CANCEL = "Cancelar";

    public static final String STR_NO_COMPARTMENT = "No hay compartimientos";
    public static final String STR_NO_PRODUCT = "No hay productos";
    public static final String STR_NO_HOSE = "No hay mangueras";

    public static final String STR_TB_USER = "TB_USER";
    public static final String STR_TB_PRODUCT = "TB_PRODUCT";
    public static final String STR_TB_VEHICLE = "TB_VEHICLE";
    public static final String STR_TB_COMPARTMENT = "TB_COMPARTMENT";
    public static final String STR_TB_MODEL_COMPARTMENT = "TB_MODEL_COMPARTMENT";
    public static final String STR_TB_REASON_COMPARTMENT = "TB_REASON_COMPARTMENT";
    public static final String STR_TB_COMPARTMENT_MANTENANCE_TYPE = "TB_COMPARTMENT_MANTENANCE_TYPE";
    public static final String STR_TB_ANDROID_CONFIGURATION = "TB_ANDROID_CONFIGURATION";
    public static final String STR_TB_STATION = "TB_STATION";
    public static final String STR_TB_HOSE = "TB_HOSE";

    /*ERROR HTTP*/
    public static final String STR_NOT_MODIFIED_ERROR = "Error 304 - El recurso no a sido modificado";
    public static final String STR_BAD_REQUEST_ERROR = "Error 400 - No se puede procesar la solicitud";
    public static final String STR_UNAUTHORIZED_ERROR = "Error 401 - No tiene autorización para el uso del servicid";
    public static final String STR_FORBIDDEN_ERROR = "Error 403 - Petición denegada";
    public static final String STR_NOT_FOUND_ERROR = "Error 404 - No se encuentra el recurso solicitado";
    public static final String STR_METHOD_NOT_ALLOWED_ERROR = "Error 405 - Método no permitido";
    public static final String STR_CONFLICT_ERROR = "Error 409 - Ocurrio un error";
    public static final String STR_UNPROCESSABLE_ERROR = "Error 422 - No se puede procesar el contenido de la solicitud";
    public static final String STR_SERVER_ERROR_ERROR = "Error 500 - Algo salio mal";
    public static final String STR_UNKNOWN_ERROR = "Error desconocido";

    /*Mensajes Globales*/
    public static final String STR_GL_BAD_DATA = "Datos incorrectos";
    public static final String STR_GL_ACCEPT_PERMITION = "Por favor, aceptar todos los permisos.";
    public static final String STR_GL_PENDING = "P";
    public static final String STR_GL_SENDING = "E";


    public static int GLOBAL_USER_ID_SESSION = 0;

    public static boolean processState = true;

    public static void hideSoftKeyboard(Context context, EditText editText){
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

}
