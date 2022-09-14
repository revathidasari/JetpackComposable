package com.example.jetpackcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.constraintlayout.compose.*
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose.naviagtion.Navigation
import com.example.jetpackcompose.ui.theme.HomeScreen
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import com.example.jetpackcompose.ui.theme.TextWhite
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.absoluteValue
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random


//To migrate compose view in xml we can use compose view in xml and extends its setcontent in the activity
//composeview.apply {setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
// setcontent { ... }}
//to use android view in compose like maps as setContent{ AndroidView(factory = {TextView(it)}) {
// textView -> textView.apply {text = "compose includes with android text"}}}

@ExperimentalMaterialApi
@ExperimentalMotionApi
@ExperimentalFoundationApi
@ExperimentalPermissionsApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val painter = painterResource(id = R.drawable.composing)
            val contentDescription = "image display"
            val title = "image display"
/*
            Button(onClick = { startActivity(Intent(this, DraggableKnobActivity::class.java)) }) {
                Text(text = "Draggable Activity Launcher")
            }
*/
            CustomLazyGrid()
            //DrawerNav()
            //BottomSheet()
            //MotionLayoutCompose()
            //ListingByScreenSize()
            //PermissionHandleRuntime()
            //MultiSelectListItems()
            //ParallaxScrollEffectByMultipleLayer()
            /*val bottomNavController = rememberNavController()
            Scaffold(bottomBar = {
                BottomNavigationBar(items = listOf(
                    BottomNavItem(name = "Home", route = "home", icon = Icons.Default.Home),
                    BottomNavItem(name = "Work", route = "work", icon = Icons.Default.AccountCircle, badgeCount = 12),
                    BottomNavItem(name = "Settings", route = "settings", icon = Icons.Default.Settings, badgeCount = 1)
                ),
                    navController = bottomNavController,
                    onItemClick =  {
                        bottomNavController.navigate(it.route)
                    })
            }) {
                     NavigationBottom(navController = bottomNavController)
            }*/
            /* navController = bottomNavController,
                 onItemClick =  {

                } )) {

            }*/
            //SplashScreenNavigation()
            //Navigation()
           // ProfileScreen()
/*            Surface(color = Color(0xFF101010), modifier = Modifier
                .fillMaxSize()) {
                DropDownWith3DAnimation(text = "Hello Animation !!..",
                modifier = Modifier.padding(15.dp)) {
                    Text(text = "This is Animating on drop down click",
                     modifier = Modifier
                         .fillMaxWidth()
                         .height(100.dp)
                         .background(Color.Red))
                }
               Box(contentAlignment = Alignment.Center) {
                    Timer(totalTime = 100L * 1000L,
                        modifier = Modifier.size(200.dp),
                        handleColor = Color.Green,
                        activeBarColor = Color(0xFF3C9923),
                        inActiveBarColor = Color.DarkGray)
                }
            }*/
           /* JetpackComposeTheme() {
              HomeScreen()
            }
*/
            /*Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
                CircularProgressBar(percentage = 0.9f, value = 100)
            }*/
            //AnimationOnButtonClick()
            //CreateConstraints()
            //ListingItems()
            //EditTextButtonSnackbar()
            //ClickBoxDownColorBoxUp()
            //ColorBox(modifier = Modifier.fillMaxSize())
            //StylingText()
/*            Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                ImageCard(painter = painter, contentDescription = contentDescription,
                    title = title)
            }*/
            //RowAndColumns()
        }
    }
}

//Ui looks at specific moment = UI state
//Redrawing UI components = Recomposing
@Composable
fun CircularProgressBar(
    percentage:Float,
    value:Int,
    fontSize : TextUnit = 24.sp,
    color : Color = Color.Cyan,
    radius : Dp = 50.dp,
    strokeWidth : Dp = 12.dp,
    animationDuration : Int = 1000,
    animationDelay : Int = 0
    ) {

    var animatePlayed by remember { mutableStateOf(false)}
    val currentPercantage = animateFloatAsState(
        targetValue = if(animatePlayed) percentage else 0f,
        animationSpec = tween(durationMillis = animationDuration, delayMillis = animationDelay))
    LaunchedEffect(key1 = true) {
        animatePlayed = true
    }
    Box(modifier = Modifier.size(radius*2f), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(radius*2f)) {
            drawArc(color = color,
                270f,//-90f
                360* currentPercantage.value,
                useCenter = false,//true
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Butt))
        }
            Text(text = (value * currentPercantage.value).toInt().toString(),
                color = Color.DarkGray,
                fontSize = fontSize,
                fontWeight = FontWeight.Bold)

    }
}

@Composable
fun AnimationOnButtonClick() {
    var sizeState by remember { mutableStateOf(200.dp) }
    val animateSize by animateDpAsState(
        targetValue = sizeState,
        tween(durationMillis = 3000)
        /*keyframes {
            durationMillis = 5000
            sizeState at 0 with LinearEasing
            sizeState * 2f at 2000 with FastOutLinearInEasing
            sizeState *3f at 5000
        }*/
/*        spring(dampingRatio = Spring.DampingRatioHighBouncy,
        stiffness = Spring.StiffnessHigh)*/
        /*tween(durationMillis = 4000,
        delayMillis = 500,
        easing = LinearOutSlowInEasing)*/
    )

    val infiniteTransistion = rememberInfiniteTransition()
    val animateColor by infiniteTransistion.animateColor(
        initialValue = Color.Gray,
        targetValue = Color.Magenta,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 3000),
             repeatMode = RepeatMode.Reverse
        ))

    Box(modifier = Modifier
        .size(animateSize)
        .background(animateColor), contentAlignment = Alignment.Center) {
        Button(onClick = {
            sizeState += 25.dp
        }) {
            Text(text = "Click to animate")
        }
    }

}


@Composable
fun CreateConstraints(){
    val constraints = ConstraintSet {
        val greenBox = createRefFor("greenbox")
        val blueBox = createRefFor("bluebox")
        val guideline = createGuidelineFromTop(0.5f)
        constrain(greenBox) {
            top.linkTo(guideline)
            start.linkTo(parent.start)
            width = Dimension.value(150.dp)
            height = Dimension.value(150.dp)
        }
        constrain(blueBox) {
            top.linkTo(parent.top)
            start.linkTo(greenBox.end)
            end.linkTo(parent.end)
            width = Dimension.value(200.dp)
            //  width = Dimension.fillToConstraints
            height = Dimension.value(175.dp)
        }
        createHorizontalChain(greenBox, blueBox, chainStyle = ChainStyle.Packed)
    }
    ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .background(Color.Green)
            .layoutId("greenbox"))
        Box(modifier = Modifier
            .background(Color.Blue)
            .layoutId("bluebox"))
    }
}
@Composable
fun ListingItems(){
//   val scrollState = rememberScrollState()
//    Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState)) {
//        for (i in 1..20) {
    LazyColumn{
        // items(200) {
        itemsIndexed(
            listOf("ab","cd","ef","gh","ij")
        ) { index, string->
            Text(text = string,//"Item it", //"Item i",
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp))
        }
    }
}
@Composable
fun EditTextButtonSnackbar(){
    //default snackbar with top display
/*    Snackbar() {
        Text(text = "snack default")
    }*/

    val scaffoldState = rememberScaffoldState()
    var textFieldState by remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    //layering compose to include existing material design components
    Scaffold(modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp)) {

            TextField(
                value = textFieldState,
                label = {
                    Text(text = "Enter the name")
                },
                onValueChange = {
                    textFieldState = it
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Hello $textFieldState")
                }
            }) {
                Text(text = "please greet me!")
            }
        }

    }
}



@Composable
fun ClickBoxDownColorBoxUp() {
    Column(modifier = Modifier.fillMaxSize()) {
        val color = remember {
            mutableStateOf(Color.Green)
        }
        Box(modifier = Modifier
            .background(color.value)
            .weight(1f)
            .fillMaxSize())
        Color2Box(modifier = Modifier
            .weight(1f)
            .fillMaxSize()) {
            color.value = it
        }
    }

}
@Composable
fun Color2Box(
    modifier: Modifier = Modifier,
    updateColor:(Color) -> Unit
) {

    Box(modifier = modifier
        .fillMaxSize()
        .background(Color.DarkGray)
        .clickable {
            updateColor(Color(
                Random.nextFloat(),
                Random.nextFloat(),
                Random.nextFloat(),
                1f
            ))

        })
}

@Composable
fun ColorBox(modifier: Modifier = Modifier) {
    //remember - reset is not need when recomposed
    val color = remember {
        mutableStateOf(Color.Blue)
    }
    Box(modifier = modifier
        .fillMaxSize()
        .background(color.value)
        .clickable {
            color.value = Color(
                Random.nextFloat(),
                Random.nextFloat(),
                Random.nextFloat(),
                1f
            )
        })
}

@Composable
fun StylingText() {
    val fontFamily = FontFamily(
        Font(R.font.qwitchergrypen_regular , FontWeight.Normal),
        Font(R.font.qwitchergrypen_bold, FontWeight.Bold)
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0XFF101010))
        .padding(start = 15.dp)) {
        Text(text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Magenta, fontSize = 50.sp)) {
                append("J")
            }
            append("etpack ")
            withStyle(style = SpanStyle(color = Color.Green, fontSize = 55.sp)) {
                append("C")
            }
            append("ompose ")
            append("Example")
        },//"Jetpack Compose Example",
        color = Color.Yellow,
        fontFamily = fontFamily,
        fontSize = 36.sp,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline)
    }
}

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        elevation = 5.dp) {
        Box(modifier = Modifier
            .height(200.dp)) {
            Image(painter = painter, contentDescription = contentDescription, contentScale = ContentScale.Crop)

            Box(modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black
                    ),
                    startY = 500f
                )))
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
                contentAlignment = Alignment.BottomStart) {
                Text(text = title, style = TextStyle(Color.White, fontSize = 20.sp))
            }
        }
    }
}

@Composable
fun RowAndColumns() {
    Column(modifier = Modifier
        .background(Color.Magenta)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Text(text = "first column",
            modifier = Modifier.offset(50.dp, 10.dp)) // offset - to move based on given values
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "second column")
    }
    Row(modifier = Modifier
        .padding(horizontal = 50.dp)
        .fillMaxWidth()
        .fillMaxHeight(0.5f)
        .border(5.dp, Color.Cyan)
        .padding(5.dp)
        .border(10.dp, Color.DarkGray)
        .padding(15.dp)) {
        Text(text = "row1", modifier = Modifier.clickable {

        })
        Text(text = "row2")
    }
}
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

//To show the preview of called methods
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeTheme {
        Greeting("Android")
    }
}


@Composable
fun Timer(
    totalTime: Long,
    modifier: Modifier,
    handleColor: Color,
    activeBarColor: Color,
    inActiveBarColor: Color,
    initialValue: Float = 0f,
    strokeWidth: Dp = 5.dp,
) {

    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    var value by remember { // current percentage of the angle
        mutableStateOf(initialValue)
    }
    var currentTime by remember {
        mutableStateOf(totalTime)
    }
    var isTimerRunning by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if (currentTime > 0L && isTimerRunning) {
            delay(100L)
            currentTime -= 100L
            value = currentTime/totalTime.toFloat()
        }
    }
    Box(contentAlignment = Alignment.Center, modifier = modifier.onSizeChanged {
        size = it
    }) {
        Canvas(modifier = modifier) {
            drawArc(color = inActiveBarColor,
                startAngle = -215f,
                sweepAngle = 250f,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round))
            drawArc(color = activeBarColor,
                startAngle = -215f,
                sweepAngle = 250f * value,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round))

            val center = Offset(size.width / 2f, size.height / 2f)
            val beta = (250f * value + 145f) * (PI / 180f).toFloat()
            val r = size.width / 2f
            val a = cos(beta) * r
            val b = sin(beta) * r

            drawPoints(
                points = listOf(Offset(center.x + a, center.y + b)),
                pointMode = PointMode.Points,
                color = handleColor,
                strokeWidth = (strokeWidth * 3f).toPx(),
                cap = StrokeCap.Round
            )
        }
        Text(text = (currentTime / 1000L).toString(),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Yellow)
        Button(onClick = {
                         if(currentTime <= 0L) {
                             currentTime = totalTime
                             isTimerRunning = true
                         } else {
                             isTimerRunning = !isTimerRunning
                         }
        },
            modifier = Modifier.align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (currentTime <= 0L || !isTimerRunning) {
                    Color.Green
                } else {
                    Color.Red
                }
            )) {
            Text(text = if (currentTime >= 0L && !isTimerRunning) "Start"
            else if (currentTime >= 0L && isTimerRunning) "Stop"
            else "Restart")
        }
    }
}

@Composable
fun DropDownWith3DAnimation(
    text : String,
    modifier: Modifier = Modifier,
    isInitallyOpened : Boolean = false,
    content : @Composable () -> Unit
) {
    var isOpen by remember {
        mutableStateOf(isInitallyOpened)
    }
    val alpha = animateFloatAsState(targetValue = if (isOpen) 1f else 0f,
        animationSpec = tween(durationMillis = 300))
    val rotateX = animateFloatAsState(targetValue = if (isOpen) 0f else -90f,
        animationSpec = tween(durationMillis = 300))
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(15.dp)) {

        Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()){
            Text(text = text,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal)

            Icon(imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Drop Down for Animation",
            modifier = Modifier
                .clickable {
                    isOpen = !isOpen
                }
                .scale(1f, if (isOpen) -1f else 1f),
            tint = Color.White)
        }

        Spacer(modifier = Modifier.height(10.dp))
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {//3d trnaformations
                    transformOrigin = TransformOrigin(0.5f, 0f)
                    rotationX = rotateX.value
                }
                .alpha(alpha.value)
                .padding(15.dp)) {
            content()
        }
    }
}
