package com.example

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        Scaffold(
          modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
          MainPortalScreen(modifier = Modifier.padding(innerPadding))
        }
      }
    }
  }
}

@Composable
fun MainPortalScreen(modifier: Modifier = Modifier) {
  val context = LocalContext.current

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(DarkSpaceBg)
  ) {
    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      contentPadding = PaddingValues(bottom = 32.dp)
    ) {
      // 1. Premium Custom Top Bar
      item {
        CustomTopBar()
      }

      // 2. Cinematic Hero Section with overlay
      item {
        HeroBannerSection()
      }

      // 3. Direct Downloads Title & Divider
      item {
        SectionTitle(
          title = "باقة التحميل الرسمية",
          subtitle = "قم بتحميل التطبيق والمشغل معاً لضمان العمل بنجاح"
        )
      }

      // 4. Download Option: YAHIA TV App
      item {
        DownloadCard(
          title = "تطبيق YAHIA TV",
          version = "v1.8.0 (الأحدث)",
          size = "31.32 ميجابايت",
          description = "بوابتك المتكاملة لمشاهدة جميع القنوات العربية والعالمية، الأفلام والمسلسلات بجودة عالية وبدون تقطيع.",
          iconRes = R.drawable.img_yahia_tv_logo,
          downloadUrl = "https://www.mediafire.com/file/st0wz11ug19x3h2/Yahia_TV.apk/file",
          borderColor = GoldAccent,
          badgeColor = GoldAccent,
          badgeText = "الرئيسي",
          testTagPrefix = "yahia_tv"
        )
      }

      // 5. Download Option: YAHIA PLAYER
      item {
        DownloadCard(
          title = "مشغل YAHIA PLAYER",
          version = "v1.5.2 (الأحدث)",
          size = "22.51 ميجابايت",
          description = "محرك الفيديوهات والمسؤول عن فك ترميز قنوات البث المباشر. لا يعمل تطبيق YAHIA TV بدونه.",
          iconRes = R.drawable.img_yahia_player_logo,
          downloadUrl = "https://www.mediafire.com/file/m26e0ajh3hu2yb4/YAHIA_Player.apk/file",
          borderColor = CyanPlayer,
          badgeColor = CyanPlayer,
          badgeText = "إجباري للتشغيل",
          testTagPrefix = "yahia_player",
          isRequiredNotice = true
        )
      }

      // 6. Installation Progress Guide
      item {
        SectionTitle(
          title = "دليل التثبيت والتشغيل بالتفصيل",
          subtitle = "اتبع الخطوات التالية بدقة لضمان عمل التطبيق بسلاسة"
        )
      }

      item {
        InstallationGuideSection()
      }

      // 7. Interactive troubleshooting & FAQs
      item {
        SectionTitle(
          title = "الأسئلة الشائعة وحل المشكلات",
          subtitle = "إجابات وحلول سريعة لأكثر الاستفسارات شيوعاً"
        )
      }

      item {
        FaqSection()
      }

      // 8. Device Compatibility & Technical Specs
      item {
        SpecsSection()
      }

      // 9. Quick Actions: Copy links & Share
      item {
        QuickActionsSection()
      }

      // 10. Footer
      item {
        FooterSection()
      }
    }
  }
}

@Composable
fun CustomTopBar() {
  val context = LocalContext.current

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .background(DarkSpaceBg)
      .padding(horizontal = 16.dp, vertical = 14.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    // Left side: Share Icon Button
    IconButton(
      onClick = {
        val shareText = """
          🌟 قم بتنزيل تطبيق البث الرائع YAHIA TV ومشغله الرسمي YAHIA PLAYER للأندرويد بروابط تحميل مباشرة مجاناً:
          
          1️⃣ تطبيق YAHIA TV (31.32 MB):
          https://www.mediafire.com/file/st0wz11ug19x3h2/Yahia_TV.apk/file
          
          2️⃣ مشغل YAHIA PLAYER (22.51 MB):
          https://www.mediafire.com/file/m26e0ajh3hu2yb4/YAHIA_Player.apk/file
          
          📺 مشاهدة ممتعة لجميع القنوات والمسلسلات!
        """.trimIndent()
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
          type = "text/plain"
          putExtra(Intent.EXTRA_TEXT, shareText)
        }
        context.startActivity(Intent.createChooser(shareIntent, "مشاركة روابط التحميل عبر"))
      },
      modifier = Modifier
        .clip(CircleShape)
        .background(DarkCardBg)
        .border(1.dp, DarkCardBorder, CircleShape)
        .testTag("bar_share_btn")
    ) {
      Icon(
        imageVector = Icons.Default.Share,
        contentDescription = "مشاركة روابط التطبيق والمشغل",
        tint = GoldAccent
      )
    }

    // Right side: Brand Label and logo (RTL layout)
    Row(
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier.padding(end = 12.dp)
      ) {
        Text(
          text = "YAHIA TV",
          style = MaterialTheme.typography.titleMedium,
          fontWeight = FontWeight.Bold,
          color = GoldAccent,
          letterSpacing = 1.sp
        )
        Text(
          text = "بوابة التحميل والدعم الرسمي",
          style = MaterialTheme.typography.bodySmall,
          color = TextMuted
        )
      }

      Image(
        painter = painterResource(id = R.drawable.img_yahia_tv_logo),
        contentDescription = "YAHIA TV Logo Logo",
        modifier = Modifier
          .size(42.dp)
          .clip(RoundedCornerShape(10.dp))
          .border(1.5.dp, GoldAccent, RoundedCornerShape(10.dp)),
        contentScale = ContentScale.Crop
      )
    }
  }
}

@Composable
fun HeroBannerSection() {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(220.dp)
      .padding(horizontal = 16.dp, vertical = 8.dp)
      .clip(RoundedCornerShape(20.dp))
      .border(1.dp, DarkCardBorder, RoundedCornerShape(20.dp))
  ) {
    // Background Hero image
    Image(
      painter = painterResource(id = R.drawable.img_tv_banner),
      contentDescription = "Cinema and Smart TV Streaming Banner",
      modifier = Modifier.fillMaxSize(),
      contentScale = ContentScale.Crop
    )

    // Dark gradient overlay for typography readability
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(
          Brush.verticalGradient(
            colors = listOf(
              Color.Transparent,
              Color.Black.copy(alpha = 0.5f),
              Color.Black.copy(alpha = 0.95f)
            )
          )
        )
    )

    // Content overlay aligned to bottom right (RTL design)
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
      verticalArrangement = Arrangement.Bottom,
      horizontalAlignment = Alignment.End
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
          .background(GoldAccent.copy(alpha = 0.2f), RoundedCornerShape(6.dp))
          .padding(horizontal = 8.dp, vertical = 4.dp)
      ) {
        Icon(
          imageVector = Icons.Default.PlayArrow,
          contentDescription = "بث مباشر",
          tint = GoldAccent,
          modifier = Modifier.size(12.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
          text = "سينما وقنوات لايف مدمجة",
          style = MaterialTheme.typography.labelSmall,
          color = GoldAccent,
          fontWeight = FontWeight.Bold
        )
      }

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = "YAHIA TV & PLAYER",
        style = MaterialTheme.typography.headlineSmall,
        color = TextWhite,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.End
      )

      Text(
        text = "المنصة المتكاملة الأحدث لمشاهدة ممتعة ومستقرة للأندرويد",
        style = MaterialTheme.typography.bodySmall,
        color = TextWhite.copy(alpha = 0.8f),
        textAlign = TextAlign.End
      )
    }
  }
}

@Composable
fun SectionTitle(title: String, subtitle: String) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 16.dp),
    horizontalAlignment = Alignment.End
  ) {
    Text(
      text = title,
      style = MaterialTheme.typography.titleLarge,
      fontWeight = FontWeight.Bold,
      color = GoldAccent,
      textAlign = TextAlign.End
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
      text = subtitle,
      style = MaterialTheme.typography.bodyMedium,
      color = TextMuted,
      textAlign = TextAlign.End
    )
  }
}

@Composable
fun DownloadCard(
  title: String,
  version: String,
  size: String,
  description: String,
  iconRes: Int,
  downloadUrl: String,
  borderColor: Color,
  badgeColor: Color,
  badgeText: String,
  testTagPrefix: String,
  isRequiredNotice: Boolean = false
) {
  val context = LocalContext.current

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 8.dp)
      .border(1.dp, borderColor.copy(alpha = 0.4f), RoundedCornerShape(16.dp)),
    colors = CardDefaults.cardColors(containerColor = DarkCardBg),
    shape = RoundedCornerShape(16.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
      // Top row: App Icon, Branding Details, Badge (RTL structure)
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
      ) {
        // Left: Category Badge / Indicator
        Box(
          modifier = Modifier
            .background(badgeColor.copy(alpha = 0.15f), RoundedCornerShape(6.dp))
            .border(0.5.dp, badgeColor.copy(alpha = 0.5f), RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
          Text(
            text = badgeText,
            style = MaterialTheme.typography.labelSmall,
            color = badgeColor,
            fontWeight = FontWeight.Bold
          )
        }

        // Right: Icon & Text
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.End
        ) {
          Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.padding(end = 12.dp)
          ) {
            Text(
              text = title,
              style = MaterialTheme.typography.titleMedium,
              fontWeight = FontWeight.Bold,
              color = TextWhite,
              textAlign = TextAlign.End
            )
            Text(
              text = "الإصدار: $version",
              style = MaterialTheme.typography.bodySmall,
              color = TextMuted,
              textAlign = TextAlign.End
            )
          }

          Image(
            painter = painterResource(id = iconRes),
            contentDescription = "$title Logo",
            modifier = Modifier
              .size(52.dp)
              .clip(RoundedCornerShape(12.dp))
              .border(1.dp, borderColor.copy(alpha = 0.6f), RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
          )
        }
      }

      Spacer(modifier = Modifier.height(12.dp))

      // Content description in Arabic
      Text(
        text = description,
        style = MaterialTheme.typography.bodyMedium,
        color = TextWhite.copy(alpha = 0.9f),
        textAlign = TextAlign.End,
        modifier = Modifier.fillMaxWidth(),
        lineHeight = 20.sp
      )

      Spacer(modifier = Modifier.height(14.dp))

      // Technical Specifications Chips
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Spec 1: File size
        Box(
          modifier = Modifier
            .background(DarkCardBorder, RoundedCornerShape(4.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
          Text(
            text = "المساحة: $size",
            style = MaterialTheme.typography.bodySmall,
            color = TextWhite.copy(alpha = 0.8f)
          )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Spec 2: System format
        Box(
          modifier = Modifier
            .background(DarkCardBorder, RoundedCornerShape(4.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
          Text(
            text = "الصيغة: APK",
            style = MaterialTheme.typography.bodySmall,
            color = TextWhite.copy(alpha = 0.8f)
          )
        }
      }

      // Mandatory Alert Section for Player
      if (isRequiredNotice) {
        Spacer(modifier = Modifier.height(12.dp))
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .background(WarningRed.copy(alpha = 0.12f), RoundedCornerShape(8.dp))
            .border(0.5.dp, WarningRed.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
            .padding(10.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.End
        ) {
          Text(
            text = "تنبيه ضروري جداً: لن يشتغل تطبيق YAHIA TV بدون هذا المشغل. يرجى تثبيت الاثنين معاً.",
            style = MaterialTheme.typography.bodySmall,
            color = WarningRed,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = "تحذير هام",
            tint = WarningRed,
            modifier = Modifier.size(16.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(16.dp))

      // Main CTA button for direct mediafire download
      Button(
        onClick = {
          try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(downloadUrl))
            context.startActivity(intent)
          } catch (e: Exception) {
            Toast.makeText(context, "فشل فتح رابط التحميل. تأكد من وجود متصفح إنترنت.", Toast.LENGTH_LONG).show()
          }
        },
        modifier = Modifier
          .fillMaxWidth()
          .height(50.dp)
          .testTag("${testTagPrefix}_download_btn"),
        colors = ButtonDefaults.buttonColors(
          containerColor = borderColor,
          contentColor = DarkSpaceBg
        ),
        shape = RoundedCornerShape(10.dp)
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
        ) {
          CustomDownloadIcon(color = DarkSpaceBg, modifier = Modifier.size(20.dp))
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "تحميل مباشر عبر MediaFire",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.ExtraBold,
            color = DarkSpaceBg
          )
        }
      }
    }
  }
}

@Composable
fun CustomDownloadIcon(color: Color, modifier: Modifier = Modifier) {
  androidx.compose.foundation.Canvas(modifier = modifier) {
    val width = size.width
    val height = size.height

    // Draw the down arrow
    val arrowWidth = width * 0.35f
    val arrowStemHeight = height * 0.45f

    // Stem of the arrow
    drawRect(
      color = color,
      topLeft = Offset((width - arrowWidth) / 2, 2.dp.toPx()),
      size = Size(arrowWidth, arrowStemHeight)
    )

    // Tip of the arrow
    val path = Path().apply {
      moveTo(width * 0.2f, arrowStemHeight)
      lineTo(width * 0.8f, arrowStemHeight)
      lineTo(width * 0.5f, height * 0.78f)
      close()
    }
    drawPath(path, color = color)

    // Bottom horizontal bar
    drawRect(
      color = color,
      topLeft = Offset(width * 0.2f, height * 0.84f),
      size = Size(width * 0.6f, height * 0.1f)
    )
  }
}

@Composable
fun InstallationGuideSection() {
  val steps = listOf(
    Pair("الخطوة الأولى: تحميل الملفين من الروابط", "انقر على زر التحميل المباشر لكل من 'YAHIA TV' و 'YAHIA PLAYER' باللونين الذهبي والأزرق بالأعلى، لتنزيل ملفات الـ APK بنجاح على هاتفك أندرويد."),
    Pair("الخطوة الثانية: تفعيل المصادر المجهولة", "اذهب إلى إعدادات الهاتف -> الأمان أو الخصوصية -> قم بتفعيل خيار 'التثبيت من مصادر غير معروفة' للمتصفح الذي تستخدمه لتتمكن من تثبيت التطبيقات يدويًا."),
    Pair("الخطوة الثالثة: تثبيت المشغل أولاً", "اذهب لمدير الملفات أو مجلد التحميلات، ثم افتح ملف YAHIA PLAYER (بمساحة 22.51 ميجابايت) وقم بتثبيته. هذا المشغل يعمل كمحرك داخلي للبث المباشر وقنوات التلفاز."),
    Pair("الخطوة الرابعة: تثبيت YAHIA TV والتشغيل", "أخيرًا، قم بتثبيت ملف تطبيق YAHIA TV (بمساحة 31.32 ميجابايت). افتح التطبيق مباشرة وسيعمل معك فوراً وبأعلى كفاءة اعتمادًا على المشغل الذي قمت بتثبيته.")
  )

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 4.dp),
    colors = CardDefaults.cardColors(containerColor = DarkCardBg),
    shape = RoundedCornerShape(16.dp),
    border = borderStroke()
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
      steps.forEachIndexed { index, step ->
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
          horizontalArrangement = Arrangement.End,
          verticalAlignment = Alignment.Top
        ) {
          // Left: Step Details (Arabic layout)
          Column(
            modifier = Modifier
              .weight(1f)
              .padding(end = 12.dp),
            horizontalAlignment = Alignment.End
          ) {
            Text(
              text = step.first,
              style = MaterialTheme.typography.titleSmall,
              fontWeight = FontWeight.Bold,
              color = if (index % 2 == 0) GoldAccent else CyanPlayer,
              textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = step.second,
              style = MaterialTheme.typography.bodySmall,
              color = TextWhite.copy(alpha = 0.8f),
              textAlign = TextAlign.End,
              lineHeight = 18.sp
            )
          }

          // Right: Circle Number Badge
          Box(
            modifier = Modifier
              .size(32.dp)
              .background(
                if (index % 2 == 0) GoldAccent.copy(alpha = 0.2f) else CyanPlayer.copy(alpha = 0.2f),
                CircleShape
              )
              .border(
                1.5.dp,
                if (index % 2 == 0) GoldAccent else CyanPlayer,
                CircleShape
              ),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = (index + 1).toString(),
              style = MaterialTheme.typography.bodyMedium,
              fontWeight = FontWeight.Bold,
              color = if (index % 2 == 0) GoldAccent else CyanPlayer
            )
          }
        }

        // Draw connecting line between badges if not the last step
        if (index < steps.lastIndex) {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .padding(end = 15.dp),
            contentAlignment = Alignment.CenterEnd
          ) {
            Box(
              modifier = Modifier
                .width(1.5.dp)
                .height(20.dp)
                .background(TextMuted.copy(alpha = 0.4f))
            )
          }
        }
      }
    }
  }
}

@Composable
fun borderStroke(): androidx.compose.foundation.BorderStroke {
  return androidx.compose.foundation.BorderStroke(1.dp, DarkCardBorder)
}

@Composable
fun FaqSection() {
  val faqs = listOf(
    FaqItem(
      "لماذا يتطلب التطبيق تثبيت مشغل YAHIA PLAYER منفصلاً؟",
      "تم تصميم مشغل YAHIA PLAYER كمحرك منفصل لفك ترميز صيغ قنوات التلفزيون المباشر بجودة 4K وفول إتش دي. هذا يضمن بقاء تطبيق التلفزيون خفيف المساحة ومستقر، ويقلل جداً من استهلاك بطارية الهاتف مع القنوات المشفرة."
    ),
    FaqItem(
      "يظهر لي تحذير 'حظر بواسطة Play Protect' أثناء التثبيت، ماذا أفعل؟",
      "هذا التحذير طبيعي جداً عند تثبيت تطبيقات من خارج متجر جوجل بلاي الرسمي. تطبيقنا آمن وخالٍ من أي فيروسات. لتخطي هذا التحذير، اضغط على 'التفاصيل' ثم اختر 'التثبيت على أي حال' (Install anyway) ليكتمل تثبيت التطبيق بنجاح."
    ),
    FaqItem(
      "أواجه خطأ 'التطبيق ليس ثنائياً متوافقاً' أو 'لم يتم تثبيت التطبيق'؟",
      "أولاً: تأكد من تفعيل إعداد التثبيت من مصادر مجهولة. ثانياً: تأكد من خلو هاتفك من أي نسخة قديمة غير رسمية للتطبيق، وقم بمسح كاش التحميل، ثم تأكد من توفر مساحة خالية على ذاكرة تخزين الهاتف تزيد عن 100 ميجابايت."
    ),
    FaqItem(
      "هل يعمل تطبيق YAHIA TV على شاشات التلفاز الذكية؟",
      "نعم، كلاً من تطبيق YAHIA TV والمشغل يدعمان الشاشات التي تعمل بنظام Android TV وأجهزة Xiaomi Mi Box و Amazon Firestick بالكامل. يمكنك نسخ ملفات الـ APK إلى فلاش ميموري وتوصيلها بالتلفزيون وتثبيتهما بسهولة."
    )
  )

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    faqs.forEachIndexed { index, faq ->
      FaqAccordionCard(faq = faq, index = index)
    }
  }
}

data class FaqItem(val question: String, val answer: String)

@Composable
fun FaqAccordionCard(faq: FaqItem, index: Int) {
  var expanded by remember { mutableStateOf(false) }
  val rotationState by animateFloatAsState(
    targetValue = if (expanded) 180f else 0f,
    label = "arrow_rotation"
  )

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { expanded = !expanded }
      .border(1.dp, DarkCardBorder, RoundedCornerShape(12.dp)),
    colors = CardDefaults.cardColors(containerColor = DarkCardBg),
    shape = RoundedCornerShape(12.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Left: Arrow indicator
        Icon(
          imageVector = Icons.Default.ArrowDropDown,
          contentDescription = "عرض التفاصيل",
          tint = if (expanded) GoldAccent else TextMuted,
          modifier = Modifier
            .rotate(rotationState)
            .size(24.dp)
        )

        // Right: Question text
        Text(
          text = faq.question,
          style = MaterialTheme.typography.bodyMedium,
          fontWeight = FontWeight.Bold,
          color = if (expanded) GoldAccent else TextWhite,
          textAlign = TextAlign.End,
          modifier = Modifier.weight(1f).padding(start = 12.dp, end = 4.dp)
        )
      }

      // Collapsible answer block
      AnimatedVisibility(visible = expanded) {
        Column {
          Spacer(modifier = Modifier.height(10.dp))
          HorizontalDivider(color = DarkCardBorder, thickness = 1.dp)
          Spacer(modifier = Modifier.height(10.dp))
          Text(
            text = faq.answer,
            style = MaterialTheme.typography.bodySmall,
            color = TextWhite.copy(alpha = 0.85f),
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth(),
            lineHeight = 18.sp
          )
        }
      }
    }
  }
}

@Composable
fun SpecsSection() {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 16.dp)
      .border(1.dp, DarkCardBorder, RoundedCornerShape(16.dp)),
    colors = CardDefaults.cardColors(containerColor = DarkCardBg),
    shape = RoundedCornerShape(16.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      horizontalAlignment = Alignment.End
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
      ) {
        Text(
          text = "المواصفات التقنية ومتطلبات التشغيل",
          style = MaterialTheme.typography.titleMedium,
          fontWeight = FontWeight.Bold,
          color = TextWhite,
          textAlign = TextAlign.End
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
          imageVector = Icons.Default.Info,
          contentDescription = "معلومات",
          tint = CyanPlayer,
          modifier = Modifier.size(20.dp)
        )
      }

      Spacer(modifier = Modifier.height(12.dp))

      val specs = listOf(
        Pair("نظام التشغيل المدعوم", "أندرويد موبايل + أندرويد تي في (Android 5.0 فأحدث)"),
        Pair("مجموع المساحة المطلوبة", "حوالي 54 ميجابايت (التطبيق 31.32MB + المشغل 22.51MB)"),
        Pair("دقة بث الفيديو المدعومة", "SD, HD, FHD, Ultra HD (4K)"),
        Pair("مستوى الحماية والأمان", "آمن 100% ومفحوص تماماً ضد الفيروسات وضد البرمجيات الضارة"),
        Pair("فئة ترخيص الاستخدام", "مجاني بالكامل وبدون أي إعلانات منبثقة مزعجة")
      )

      specs.forEach { spec ->
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          // Value (Left)
          Text(
            text = spec.second,
            style = MaterialTheme.typography.bodySmall,
            color = TextWhite.copy(alpha = 0.9f),
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(1.3f)
          )

          // Property Name (Right)
          Text(
            text = spec.first,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            color = GoldAccent,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(0.7f).padding(start = 8.dp)
          )
        }
      }
    }
  }
}

@Composable
fun QuickActionsSection() {
  val context = LocalContext.current

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    Button(
      onClick = {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(
          "Yahia TV Direct Links",
          "تطبيق YAHIA TV: https://www.mediafire.com/file/st0wz11ug19x3h2/Yahia_TV.apk/file\nالمشغل YAHIA PLAYER: https://www.mediafire.com/file/m26e0ajh3hu2yb4/YAHIA_Player.apk/file"
        )
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "تم نسخ روابط التحميل المباشرة إلى الحافظة!", Toast.LENGTH_SHORT).show()
      },
      modifier = Modifier
        .fillMaxWidth()
        .height(48.dp)
        .testTag("copy_links_btn"),
      colors = ButtonDefaults.buttonColors(
        containerColor = DarkCardBg,
        contentColor = GoldAccent
      ),
      shape = RoundedCornerShape(10.dp),
      border = borderStroke()
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
      ) {
        Icon(
          imageVector = Icons.Default.Check,
          contentDescription = "نسخ",
          modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "نسخ روابط التحميل المباشرة",
          style = MaterialTheme.typography.bodyMedium,
          fontWeight = FontWeight.Bold
        )
      }
    }
  }
}

@Composable
fun FooterSection() {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 32.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    HorizontalDivider(color = DarkCardBorder, thickness = 1.dp)
    Spacer(modifier = Modifier.height(16.dp))
    Text(
      text = "تم التطوير والدعم بواسطة بوابة YAHIA TV الرسمية",
      style = MaterialTheme.typography.bodySmall,
      color = TextMuted,
      textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
      text = "جميع الحقوق محفوظة © 2026",
      style = MaterialTheme.typography.bodySmall,
      color = TextMuted.copy(alpha = 0.7f),
      textAlign = TextAlign.Center
    )
  }
}

