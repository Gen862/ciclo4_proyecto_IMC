package com.example.appentrega;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class Activity_Concejos extends AppCompatActivity {
    ImageView imagen;
    TextView Tx_estadopeso;
    String estadoPeso,estado1,estado2,estado3,estado4,estado5,estado6; // se le asigna a cada registro un valor de estado de peso
    Button regresar;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concejos);
        imagen = (ImageView) findViewById(R.id.estadopeso);
        Tx_estadopeso= (TextView) findViewById(R.id.concejos);
        regresar =(Button) findViewById(R.id.volver);
        estado1 ="Estás en : Bajo Peso";
        estado2 ="Estás en: Peso Normal";
        estado3="Estás en:Sobrepeso";
        estado4="Estás en:Obesidad grado 1";
        estado5="Estás en: Obesidad grado 2";
        estado6="Estás en: Obesidad Morbidad o grado 3";
        Intent intentc;
        intentc = getIntent();
        estadoPeso= intentc.getStringExtra("estado");
        if (Objects.equals(estadoPeso, estado1)) {
            Tx_estadopeso.setText(new StringBuilder().append("Estos son algunos consejos saludables para aumentar de peso si tienes bajo peso:")
                    .append("Come con más frecuencia. Consume de cinco a seis comidas más pequeñas durante el día en lugar de dos o tres comidas grandes")
                    . append("Como parte de una dieta saludable en general, elige panes, pastas y cereales integrales, frutas y vegetales, productos lácteos, fuentes de proteínas magra y nueces y semillas.")
                    .append("Agrega extras a tus platos para obtener más calorías, como queso en los guisos y huevos revueltos y leche en polvo descremada en sopas y guisos.")
                    .append("Incluso si tienes bajo peso, ten cuidado con el exceso de azúcar y grasa. Una porción de pastel con helado está bien de vez en cuando. Pero la mayoría de las golosinas deben ser saludables y proporcionar nutrientes, además de calorías. Las magdalenas de salvado, el yogurt y las barritas de granola son buenas opciones")
                    .append("El ejercicio, especialmente el fortalecimiento muscular, puede ayudarte a aumentar de peso al fortalecer los músculos. El ejercicio también puede estimular tu apetito.")
                    .append("Aunque estar delgado a menudo puede ser saludable, tener bajo peso puede ser una preocupación si es el resultado de una mala nutrición o si estás embarazada o tienes otros problemas de salud. Por lo tanto, si tienes bajo peso, consulta a tu médico o dietista para hacerte una evaluación. Juntos, pueden planificar cómo llegar a tu peso ideal.").toString());
            imagen.setImageDrawable(getDrawable(R.drawable.colon));
        }
        else
        if (Objects.equals(estadoPeso, estado2)){
            Tx_estadopeso.setText(new StringBuilder().append("Estas son algunos consejos saludables para mantener tu peso de forma saludable:")
                    .append("•\tLa variedad en la alimentación ayuda al equilibrio y a la salud. Ningún alimento proporciona todos los nutrientes que el cuerpo necesita. En su lista de alimentos verifique la variedad:\n" +
                            "cuantos diferentes tipos de alimentos está consumiendo\n")
                    .append("•\tLa cantidad de calorías que consume en el día es importante, cada alimento cuenta.\n")
                    .append("•\tLos alimentos que más calorías proporcionan son aquellos con mayor contenido de grasa y de azúcar. Para mantener un peso saludable evite los alimentos densos en calorías.  Y evite las bebidas azucaradas.")
                    .append("•\tLas grasas saturadas y grasas trans son nocivas para la salud. Limitar este tipo de grasas ayuda a controlar las calorías en la alimentación y a prevenir riesgos de enfermedades.  Entre los alimentos ricos en grasa saturada están las carnes rojas y los productos lácteos enteros, el aceite de coco y de palma.  Las grasas trans se encuentran en la grasa vegetal endurecida, manteca, margarina y productos elaborados como bizcochos, galletas, ponqués\n")
                    .append("•\tEl organismo requiere calorías para su funcionamiento normal y para la actividad física. La actividad física es todo movimiento corporal que haga trabajar el cuerpo. La actividad física cotidiana de una persona está repartida en varias áreas: la actividad que realiza en su jornada laboral, la actividad que realiza como desplazamiento o transporte; la actividad que realiza en el hogar como parte de las tareas cotidianas y la actividad recreativa que realiza en su tiempo libre.\n")
                    .append("Si tiene dudas o hay preocupación acerca de su peso, consulte al servicio de salud para recibir consejería y tratamiento adecuado."));
            imagen.setImageDrawable(getDrawable(R.drawable.ilustracion));
        }
        else
        if (Objects.equals(estadoPeso, estado3)){
            Tx_estadopeso.setText(new StringBuilder().append("Estos son algunos consejos saludables a tomar en cuenta  si tienes sobre peso:")
                    .append("•\tRealiza entre 4 o 5 ingestas al día para evitar llegar a las comidas principales con un hambre excesiva.  Recuerda que en las comidas principales, además de verduras y hortalizas, deben haber proteínas y también una pequeña porción de cereales integrales.\n")
                    .append("•\tPara prevenir la obesidad en el ámbito familiar, se recomienda que las ensaladas y verduras sean las protagonistas tanto en la comida como en la cena.\n")
                    .append("•\tAñade actividad a tu vida diaria. Lo puedes hacer evitando coger el automóvil para ir a comprar, cambiando el coche por la bici cuando sea posible y subiendo por las escaleras en lugar de utilizar el ascensor. Añade también color a tu dieta incluyendo variedad de frutas y verduras\n")
                    .append("•\tAdemás de que tus elecciones alimentarias sean adecuadas y de que evites el sedentarismo, la prevención de la obesidad también se consigue con acciones como comer en familia. Es el entorno perfecto para que tanto pequeños como mayores aprendan y tengan al alcance alimentos saludables: fruta, verdura, legumbres, cereales integrales\n")
                    .append("•\tSi tiene dudas o hay preocupación acerca de su peso, consulte al servicio de salud para recibir consejería y tratamiento adecuado."));
            imagen.setImageDrawable(getDrawable(R.drawable.unnamed));
        }
        else
        if (Objects.equals(estadoPeso, estado4)) {
            Tx_estadopeso.setText(new StringBuilder().append("Estas son algunos consejos saludables a tomar en cuenta si tienes obesidad grado I:")
                    .append("\tReducir las calorías y adoptar hábitos de alimentación más saludables es fundamental para superar la obesidad\n")
                    .append("\tAlgunos alimentos, como los postres y los alimentos procesados, contienen muchas calorías en una pequeña porción. En contraste, las frutas y verduras proporcionan un tamaño de la porción más grande con menos calorías\n")
                    .append("\tPara que tu dieta sea más saludable, come más alimentos vegetales, como frutas, verduras y granos integrales.\n")
                    .append("\tPregúntale a tu médico qué planes de alimentación son eficaces y cuáles podrían resultarte útiles.\n")
                    .append("\tLas personas con obesidad necesitan realizar al menos 150 minutos a la semana de actividad física de intensidad moderada para prevenir un mayor aumento de peso o para mantener la pérdida de una cantidad modesta de peso.\n")
                    .append("\tAunque el ejercicio aeróbico es la manera más eficiente de quemar calorías y perder el exceso de peso, todo movimiento adicional ayuda a quemar calorías.\n")
                    .append("\tHablar con un profesional de la salud mental puede ayudarte a abordar los problemas emocionales y de conducta relacionados con la alimentación.\n")
                    .append("\tSi tiene dudas o hay preocupación acerca de su peso, consulte al servicio de salud para recibir consejería y tratamiento adecuado\n"));
            imagen.setImageDrawable(getDrawable(R.drawable.personas));
        }
        else
        if (estadoPeso==estado5){
            Tx_estadopeso.setText(new StringBuilder().append("Estas son algunos consejos saludables a tomar en cuenta si tienes obesidad grado II:")
                    .append("\tNo existe una dieta perfecta para perder peso. Elige una que incluya alimentos saludables y que creas que te dará buenos resultados\n")
                    .append("\tEn contraste, las frutas y verduras proporcionan un tamaño de la porción más grande con menos calorías. Al comer porciones más grandes de alimentos que tienen menos calorías, reduces la sensación de hambre, consumes menos calorías y te sientes mejor con la comida, lo que aumenta la sensación de bienestar general.\n")
                    .append("\tPregúntale a tu médico qué planes de alimentación son eficaces y cuáles podrían resultarte útiles. Las bebidas endulzadas con azúcar son una manera segura de consumir más calorías que lo deseado.\n")
                    .append("\tLas personas con obesidad necesitan realizar al menos 150 minutos a la semana de actividad física de intensidad moderada para prevenir un mayor aumento de peso o para mantener la pérdida de una cantidad modesta de peso. Es probable que necesites aumentar progresivamente la cantidad de ejercicio a medida que mejoran tu resistencia y forma física.\n")
                    .append("\tHablar con un profesional de la salud mental puede ayudarte a abordar los problemas emocionales y de conducta relacionados con la alimentación\n")
                    .append("\tSi tiene dudas o hay preocupación acerca de su peso, consulte al servicio de salud para recibir consejería y tratamiento adecuado\n"));
            imagen.setImageDrawable(getDrawable(R.drawable.estilo));
        }
        else
        if (Objects.equals(estadoPeso, estado6)){
            Tx_estadopeso.setText(new StringBuilder().append("Estas son algunos consejos saludables a tomar en cuenta si tienes obesidad grado III:")
                    .append("\tReducir las calorías y adoptar hábitos de alimentación más saludables es fundamental para superar la obesidad. No existe una dieta perfecta para perder peso. Elige una que incluya alimentos saludables y que creas que te dará buenos resultados.\n")
                    .append("\tPara que tu dieta sea más saludable, come más alimentos vegetales, como frutas, verduras y granos integrales. Además, elige fuentes magras de proteínas, como frijoles, lentejas y soja, y carnes magras\n")
                    .append("\tLas personas con obesidad necesitan realizar al menos 150 minutos a la semana de actividad física de intensidad moderada para prevenir un mayor aumento de peso o para mantener la pérdida de una cantidad modesta de peso. Es probable que necesites aumentar progresivamente la cantidad de ejercicio a medida que mejoran tu resistencia y forma física.\n")
                    .append("\tAunque el ejercicio aeróbico es la manera más eficiente de quemar calorías y perder el exceso de peso, todo movimiento adicional ayuda a quemar calorías. Estaciona más lejos de la entrada a las tiendas y toma las escaleras en lugar del ascensor.\n")
                    .append("\tAdemás, puedes aprender a supervisar tu dieta y actividad física, a comprender los desencadenantes que te llevan a comer y a afrontar los antojos. El asesoramiento puede ser individual o grupal.\n")
                    .append("\tSi tiene dudas o hay preocupación acerca de su peso, consulte al servicio de salud para recibir consejería y tratamiento adecuado\n"));
            imagen.setImageDrawable(getDrawable(R.drawable.desktop));
        }


    regresar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Activity_Concejos.this, Activity_Datos.class);
            startActivity(intent);
        }
    });


    }
}