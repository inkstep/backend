package email.templates;

public class ClientRequestTemplate implements EmailTemplate {

  @Override
  public String getTemplate() {
    StringBuffer stringBuffer = new StringBuffer();

    stringBuffer.append("<!doctype html>\n");
    stringBuffer.append("<head>\n");
    stringBuffer.append("    <meta charset=\"utf-8\">\n");
    stringBuffer.append("    <!--[if !mso]><!-->\n");
    stringBuffer.append("    <link href=\"https://fonts.googleapis.com/css?family=Roboto\" rel=\"stylesheet\" type=\"text/css\"/>\n");
    stringBuffer.append("    <!--<![endif]-->\n");
    stringBuffer.append("    <style type=\"text/css\">\n");
    stringBuffer.append("        body {\n");
    stringBuffer.append("            margin: 0;\n");
    stringBuffer.append("            padding: 0;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("\n");
    stringBuffer.append("        table,\n");
    stringBuffer.append("        td,\n");
    stringBuffer.append("        tr {\n");
    stringBuffer.append("            vertical-align: top;\n");
    stringBuffer.append("            border-collapse: collapse;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("\n");
    stringBuffer.append("        * {\n");
    stringBuffer.append("            line-height: inherit;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("\n");
    stringBuffer.append("        a[x-apple-data-detectors=true] {\n");
    stringBuffer.append("            color: inherit !important;\n");
    stringBuffer.append("            text-decoration: none !important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("\n");
    stringBuffer.append("        .ie-browser table {\n");
    stringBuffer.append("            table-layout: fixed;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("\n");
    stringBuffer.append("        [owa] .img-container div,\n");
    stringBuffer.append("        [owa] .img-container button {\n");
    stringBuffer.append("            display: block !important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("\n");
    stringBuffer.append("        [owa] .fullwidth button {\n");
    stringBuffer.append("            width: 100% !important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("\n");
    stringBuffer.append("        [owa] .block-grid .col {\n");
    stringBuffer.append("            display: table-cell;\n");
    stringBuffer.append("            float: none !important;\n");
    stringBuffer.append("            vertical-align: top;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("\n");
    stringBuffer.append("        .ie-browser .block-grid,\n");
    stringBuffer.append("        .ie-browser .num12,\n");
    stringBuffer.append("        [owa] .num12,\n");
    stringBuffer.append("        [owa] .block-grid {\n");
    stringBuffer.append("            width: 745px !important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("\n");
    stringBuffer.append("        .ie-browser .mixed-two-up .num4,\n");
    stringBuffer.append("        [owa] .mixed-two-up .num4 {\n");
    stringBuffer.append("            width: 248px !important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("\n");
    stringBuffer.append("        .ie-browser .mixed-two-up .num8,\n");
    stringBuffer.append("        [owa] .mixed-two-up .num8 {\n");
    stringBuffer.append("            width: 496px !important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("\n");
    stringBuffer.append("        .ie-browser .block-grid.two-up .col,\n");
    stringBuffer.append("        [owa] .block-grid.two-up .col {\n");
    stringBuffer.append("            width: 372px !important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("\n");
    stringBuffer.append("        .ie-browser .block-grid.three-up .col,\n");
    stringBuffer.append("        [owa] .block-grid.three-up .col {\n");
    stringBuffer.append("            width: 372px !important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("\n");
    stringBuffer
      .append("        .ie-browser .block-grid.four-up .col [owa] .block-grid.four-up .col {\n");
    stringBuffer.append("            width: 186px !important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("\n");
    stringBuffer
      .append("        .ie-browser .block-grid.five-up .col [owa] .block-grid.five-up .col {\n");
    stringBuffer.append("            width: 149px !important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("\n");
    stringBuffer.append("        .ie-browser .block-grid.six-up .col,\n");
    stringBuffer.append("        [owa] .block-grid.six-up .col {\n");
    stringBuffer.append("            width: 124px !important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("\n");
    stringBuffer.append("        .ie-browser .block-grid.seven-up .col,\n");
    stringBuffer.append("        [owa] .block-grid.seven-up .col {\n");
    stringBuffer.append("            width: 106px !important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("\n");
    stringBuffer.append("        .ie-browser .block-grid.eight-up .col,\n");
    stringBuffer.append("        [owa] .block-grid.eight-up .col {\n");
    stringBuffer.append("            width: 93px !important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("\n");
    stringBuffer.append("        .ie-browser .block-grid.nine-up .col,\n");
    stringBuffer.append("        [owa] .block-grid.nine-up .col {\n");
    stringBuffer.append("            width: 82px !important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("\n");
    stringBuffer.append("        .ie-browser .block-grid.ten-up .col,\n");
    stringBuffer.append("        [owa] .block-grid.ten-up .col {\n");
    stringBuffer.append("            width: 60px !important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("\n");
    stringBuffer.append("        .ie-browser .block-grid.eleven-up .col,\n");
    stringBuffer.append("        [owa] .block-grid.eleven-up .col {\n");
    stringBuffer.append("            width: 54px !important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("\n");
    stringBuffer.append("        .ie-browser .block-grid.twelve-up .col,\n");
    stringBuffer.append("        [owa] .block-grid.twelve-up .col {\n");
    stringBuffer.append("            width: 50px !important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("    </style>\n");
    stringBuffer.append("    <style id=\"media-query\" type=\"text/css\">\n");
    stringBuffer.append("        @media only screen and (min-width: 765px) {\n");
    stringBuffer.append("            .block-grid {\n");
    stringBuffer.append("                width: 745px !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .block-grid .col {\n");
    stringBuffer.append("                vertical-align: top;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .block-grid .col.num12 {\n");
    stringBuffer.append("                width: 745px !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .block-grid.mixed-two-up .col.num3 {\n");
    stringBuffer.append("                width: 186px !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .block-grid.mixed-two-up .col.num4 {\n");
    stringBuffer.append("                width: 248px !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .block-grid.mixed-two-up .col.num8 {\n");
    stringBuffer.append("                width: 496px !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .block-grid.mixed-two-up .col.num9 {\n");
    stringBuffer.append("                width: 558px !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .block-grid.two-up .col {\n");
    stringBuffer.append("                width: 372px !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .block-grid.three-up .col {\n");
    stringBuffer.append("                width: 248px !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .block-grid.four-up .col {\n");
    stringBuffer.append("                width: 186px !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .block-grid.five-up .col {\n");
    stringBuffer.append("                width: 149px !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .block-grid.six-up .col {\n");
    stringBuffer.append("                width: 124px !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .block-grid.seven-up .col {\n");
    stringBuffer.append("                width: 106px !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .block-grid.eight-up .col {\n");
    stringBuffer.append("                width: 93px !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .block-grid.nine-up .col {\n");
    stringBuffer.append("                width: 82px !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .block-grid.ten-up .col {\n");
    stringBuffer.append("                width: 74px !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .block-grid.eleven-up .col {\n");
    stringBuffer.append("                width: 67px !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .block-grid.twelve-up .col {\n");
    stringBuffer.append("                width: 62px !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("\n");
    stringBuffer.append("        @media (max-width: 765px) {\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .block-grid,\n");
    stringBuffer.append("            .col {\n");
    stringBuffer.append("                min-width: 320px !important;\n");
    stringBuffer.append("                max-width: 100% !important;\n");
    stringBuffer.append("                display: block !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .block-grid {\n");
    stringBuffer.append("                width: 100% !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .col {\n");
    stringBuffer.append("                width: 100% !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .col>div {\n");
    stringBuffer.append("                margin: 0 auto;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            img.fullwidth,\n");
    stringBuffer.append("            img.fullwidthOnMobile {\n");
    stringBuffer.append("                max-width: 100% !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .no-stack .col {\n");
    stringBuffer.append("                min-width: 0 !important;\n");
    stringBuffer.append("                display: table-cell !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .no-stack.two-up .col {\n");
    stringBuffer.append("                width: 50% !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .no-stack .col.num4 {\n");
    stringBuffer.append("                width: 33% !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .no-stack .col.num8 {\n");
    stringBuffer.append("                width: 66% !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .no-stack .col.num4 {\n");
    stringBuffer.append("                width: 33% !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .no-stack .col.num3 {\n");
    stringBuffer.append("                width: 25% !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .no-stack .col.num6 {\n");
    stringBuffer.append("                width: 50% !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .no-stack .col.num9 {\n");
    stringBuffer.append("                width: 75% !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .video-block {\n");
    stringBuffer.append("                max-width: none !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .mobile_hide {\n");
    stringBuffer.append("                min-height: 0px;\n");
    stringBuffer.append("                max-height: 0px;\n");
    stringBuffer.append("                max-width: 0px;\n");
    stringBuffer.append("                display: none;\n");
    stringBuffer.append("                overflow: hidden;\n");
    stringBuffer.append("                font-size: 0px;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("\n");
    stringBuffer.append("            .desktop_hide {\n");
    stringBuffer.append("                display: block !important;\n");
    stringBuffer.append("                max-height: none !important;\n");
    stringBuffer.append("            }\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("    </style>\n");
    stringBuffer.append("</head>\n");
    stringBuffer.append(
      "<body class=\"clean-body\" style=\"margin: 0; padding: 0; -webkit-text-size-adjust: 100%; background-color: #202431;\">\n");
    stringBuffer.append("<style id=\"media-query-bodytag\" type=\"text/css\">\n");
    stringBuffer.append("    @media (max-width: 765px) {\n");
    stringBuffer.append("        .block-grid {\n");
    stringBuffer.append("            min-width: 320px!important;\n");
    stringBuffer.append("            max-width: 100%!important;\n");
    stringBuffer.append("            width: 100%!important;\n");
    stringBuffer.append("            display: block!important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("        .col {\n");
    stringBuffer.append("            min-width: 320px!important;\n");
    stringBuffer.append("            max-width: 100%!important;\n");
    stringBuffer.append("            width: 100%!important;\n");
    stringBuffer.append("            display: block!important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("        .col > div {\n");
    stringBuffer.append("            margin: 0 auto;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("        img.fullwidth {\n");
    stringBuffer.append("            max-width: 100%!important;\n");
    stringBuffer.append("            height: auto!important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("        img.fullwidthOnMobile {\n");
    stringBuffer.append("            max-width: 100%!important;\n");
    stringBuffer.append("            height: auto!important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("        .no-stack .col {\n");
    stringBuffer.append("            min-width: 0!important;\n");
    stringBuffer.append("            display: table-cell!important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("        .no-stack.two-up .col {\n");
    stringBuffer.append("            width: 50%!important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("        .no-stack.mixed-two-up .col.num4 {\n");
    stringBuffer.append("            width: 33%!important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("        .no-stack.mixed-two-up .col.num8 {\n");
    stringBuffer.append("            width: 66%!important;\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("        .no-stack.three-up .col.num4 {\n");
    stringBuffer.append("            width: 33%!important\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("        .no-stack.four-up .col.num3 {\n");
    stringBuffer.append("            width: 25%!important\n");
    stringBuffer.append("        }\n");
    stringBuffer.append("    }\n");
    stringBuffer.append("</style>\n");
    stringBuffer.append("<!--[if IE]><div class=\"ie-browser\"><![endif]-->\n");
    stringBuffer.append(
      "<table bgcolor=\"#202431\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; min-width: 320px; Margin: 0 auto; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #202431; width: 100%;\" valign=\"top\" width=\"100%\">\n");
    stringBuffer.append("    <tbody>\n");
    stringBuffer.append("    <tr style=\"vertical-align: top;\" valign=\"top\">\n");
    stringBuffer.append(
      "        <td style=\"word-break: break-word; vertical-align: top; border-collapse: collapse;\" valign=\"top\">\n");
    stringBuffer.append(
      "            <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color:#202431\"><![endif]-->\n");
    stringBuffer.append("            <div style=\"background-color:#f6f6f4;\">\n");
    stringBuffer.append(
      "                <div class=\"block-grid\" style=\"Margin: 0 auto; min-width: 320px; max-width: 745px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: transparent;;\">\n");
    stringBuffer.append(
      "                    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#f6f6f4;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:745px\"><tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]><td align=\"center\" width=\"745\" style=\"background-color:transparent;width:745px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:0px; padding-bottom:5px;\"><![endif]-->\n");
    stringBuffer.append(
      "                        <div class=\"col num12\" style=\"min-width: 320px; max-width: 745px; display: table-cell; vertical-align: top;;\">\n");
    stringBuffer.append("                            <div style=\"width:100% !important;\">\n");
    stringBuffer.append("                                <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer.append(
      "                                <div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:0px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n");
    stringBuffer.append("                                    <!--<![endif]-->\n");
    stringBuffer.append(
      "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n");
    stringBuffer.append("                                        <tbody>\n");
    stringBuffer.append(
      "                                        <tr style=\"vertical-align: top;\" valign=\"top\">\n");
    stringBuffer.append(
      "                                            <td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px; border-collapse: collapse;\" valign=\"top\">\n");
    stringBuffer.append(
      "                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"0\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; border-top: 0px solid transparent; height: 0px;\" valign=\"top\" width=\"100%\">\n");
    stringBuffer.append("                                                    <tbody>\n");
    stringBuffer.append(
      "                                                    <tr style=\"vertical-align: top;\" valign=\"top\">\n");
    stringBuffer.append(
      "                                                        <td height=\"0\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; border-collapse: collapse;\" valign=\"top\"><span></span></td>\n");
    stringBuffer.append("                                                    </tr>\n");
    stringBuffer.append("                                                    </tbody>\n");
    stringBuffer.append("                                                </table>\n");
    stringBuffer.append("                                            </td>\n");
    stringBuffer.append("                                        </tr>\n");
    stringBuffer.append("                                        </tbody>\n");
    stringBuffer.append("                                    </table>\n");
    stringBuffer.append("                                    <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer.append("                                </div>\n");
    stringBuffer.append("                                <!--<![endif]-->\n");
    stringBuffer.append("                            </div>\n");
    stringBuffer.append("                        </div>\n");
    stringBuffer
      .append("                        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n");
    stringBuffer.append("                    </div>\n");
    stringBuffer.append("                </div>\n");
    stringBuffer.append("            </div>\n");
    stringBuffer.append("            <div style=\"background-color:transparent;\">\n");
    stringBuffer.append(
      "                <div class=\"block-grid mixed-two-up\" style=\"Margin: 0 auto; min-width: 320px; max-width: 745px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: transparent;;\">\n");
    stringBuffer.append(
      "                    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:745px\"><tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]><td align=\"center\" width=\"186\" style=\"background-color:transparent;width:186px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\n");
    stringBuffer.append(
      "                        <div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 186px;;\">\n");
    stringBuffer.append("                            <div style=\"width:100% !important;\">\n");
    stringBuffer.append("                                <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer.append(
      "                                <div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n");
    stringBuffer.append("                                    <!--<![endif]-->\n");
    stringBuffer.append(
      "                                    <div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 10px;padding-left: 10px;\">\n");
    stringBuffer.append(
      "                                        <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr style=\"line-height:0px\"><td style=\"padding-right: 10px;padding-left: 10px;\" align=\"center\"><![endif]-->\n");
    stringBuffer.append(
      "                                        <div style=\"font-size:1px;line-height:10px\"> </div><img align=\"center\" alt=\"Image\" border=\"0\" class=\"center fixedwidth\" src=\"https://i.imgur.com/Pxt5HKD.png\" style=\"outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; clear: both; border: 0; height: auto; float: none; width: 100%; max-width: 65px; display: block;\" title=\"Image\" width=\"65\"/>\n");
    stringBuffer.append(
      "                                        <div style=\"font-size:1px;line-height:10px\"> </div>\n");
    stringBuffer.append(
      "                                        <!--[if mso]></td></tr></table><![endif]-->\n");
    stringBuffer.append("                                    </div>\n");
    stringBuffer.append("                                    <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer.append("                                </div>\n");
    stringBuffer.append("                                <!--<![endif]-->\n");
    stringBuffer.append("                            </div>\n");
    stringBuffer.append("                        </div>\n");
    stringBuffer
      .append("                        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]></td><td align=\"center\" width=\"558\" style=\"background-color:transparent;width:558px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\n");
    stringBuffer.append(
      "                        <div class=\"col num9\" style=\"display: table-cell; vertical-align: top; min-width: 320px; max-width: 558px;;\">\n");
    stringBuffer.append("                            <div style=\"width:100% !important;\">\n");
    stringBuffer.append("                                <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer.append(
      "                                <div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n");
    stringBuffer.append("                                    <!--<![endif]-->\n");
    stringBuffer.append(
      "                                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 25px; padding-left: 25px; padding-top: 25px; padding-bottom: 25px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\n");
    stringBuffer.append(
      "                                    <div style=\"color:#555555;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;line-height:120%;padding-top:25px;padding-right:25px;padding-bottom:25px;padding-left:25px;\">\n");
    stringBuffer.append(
      "                                        <div style=\"font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; font-size: 12px; line-height: 14px; color: #555555;\">\n");
    stringBuffer.append(
      "                                            <p style=\"font-size: 14px; line-height: 40px; text-align: left; margin: 0;\"><span style=\"font-size: 34px; color: #ffffff;\"><strong>Hey, {{ARTIST NAME}}!</strong></span></p>\n");
    stringBuffer.append("                                        </div>\n");
    stringBuffer.append("                                    </div>\n");
    stringBuffer
      .append("                                    <!--[if mso]></td></tr></table><![endif]-->\n");
    stringBuffer.append("                                    <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer.append("                                </div>\n");
    stringBuffer.append("                                <!--<![endif]-->\n");
    stringBuffer.append("                            </div>\n");
    stringBuffer.append("                        </div>\n");
    stringBuffer
      .append("                        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n");
    stringBuffer.append("                    </div>\n");
    stringBuffer.append("                </div>\n");
    stringBuffer.append("            </div>\n");
    stringBuffer.append("            <div style=\"background-color:transparent;\">\n");
    stringBuffer.append(
      "                <div class=\"block-grid\" style=\"Margin: 0 auto; min-width: 320px; max-width: 745px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: transparent;;\">\n");
    stringBuffer.append(
      "                    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:745px\"><tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]><td align=\"center\" width=\"745\" style=\"background-color:transparent;width:745px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:0px; padding-bottom:0px;\"><![endif]-->\n");
    stringBuffer.append(
      "                        <div class=\"col num12\" style=\"min-width: 320px; max-width: 745px; display: table-cell; vertical-align: top;;\">\n");
    stringBuffer.append("                            <div style=\"width:100% !important;\">\n");
    stringBuffer.append("                                <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer.append(
      "                                <div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:0px; padding-bottom:0px; padding-right: 0px; padding-left: 0px;\">\n");
    stringBuffer.append("                                    <!--<![endif]-->\n");
    stringBuffer.append(
      "                                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\n");
    stringBuffer.append(
      "                                    <div style=\"color:#555555;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;line-height:120%;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n");
    stringBuffer.append(
      "                                        <div style=\"font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; font-size: 12px; line-height: 14px; color: #555555;\">\n");
    stringBuffer.append(
      "                                            <p style=\"font-size: 14px; line-height: 40px; margin: 0;\"><span style=\"font-size: 34px; color: #ffffff;\"><span style=\"font-size: 20px; line-height: 24px;\">You've got a booking request from</span></span></p>\n");
    stringBuffer.append(
      "                                            <p style=\"font-size: 14px; line-height: 40px; margin: 0;\"><span style=\"font-size: 34px; color: #ffffff;\"><strong>{{CLIENT NAME}}</strong></span></p>\n");
    stringBuffer.append("                                        </div>\n");
    stringBuffer.append("                                    </div>\n");
    stringBuffer
      .append("                                    <!--[if mso]></td></tr></table><![endif]-->\n");
    stringBuffer.append("                                    <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer.append("                                </div>\n");
    stringBuffer.append("                                <!--<![endif]-->\n");
    stringBuffer.append("                            </div>\n");
    stringBuffer.append("                        </div>\n");
    stringBuffer
      .append("                        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n");
    stringBuffer.append("                    </div>\n");
    stringBuffer.append("                </div>\n");
    stringBuffer.append("            </div>\n");
    stringBuffer.append("            <div style=\"background-color:#F6F6F4;\">\n");
    stringBuffer.append(
      "                <div class=\"block-grid\" style=\"Margin: 0 auto; min-width: 320px; max-width: 745px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: transparent;;\">\n");
    stringBuffer.append(
      "                    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#F6F6F4;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:745px\"><tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]><td align=\"center\" width=\"745\" style=\"background-color:transparent;width:745px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\n");
    stringBuffer.append(
      "                        <div class=\"col num12\" style=\"min-width: 320px; max-width: 745px; display: table-cell; vertical-align: top;;\">\n");
    stringBuffer.append("                            <div style=\"width:100% !important;\">\n");
    stringBuffer.append("                                <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer.append(
      "                                <div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n");
    stringBuffer.append("                                    <!--<![endif]-->\n");
    stringBuffer.append(
      "                                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 0px; padding-bottom: 0px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\n");
    stringBuffer.append(
      "                                    <div style=\"color:#202431;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;line-height:150%;padding-top:0px;padding-right:10px;padding-bottom:0px;padding-left:10px;\">\n");
    stringBuffer.append(
      "                                        <div style=\"font-size: 12px; line-height: 18px; font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; color: #202431;\">\n");
    stringBuffer.append(
      "                                            <p style=\"font-size: 14px; line-height: 45px; text-align: left; margin: 0;\"><span style=\"font-size: 30px;\">Concept.</span></p>\n");
    stringBuffer.append("                                        </div>\n");
    stringBuffer.append("                                    </div>\n");
    stringBuffer
      .append("                                    <!--[if mso]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 5px; padding-bottom: 15px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\n");
    stringBuffer.append(
      "                                    <div style=\"color:#202431;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;line-height:180%;padding-top:5px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n");
    stringBuffer.append(
      "                                        <div style=\"line-height: 21px; font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; font-size: 12px; color: #202431;\">\n");
    stringBuffer.append(
      "                                            <p style=\"font-size: 14px; line-height: 28px; text-align: left; margin: 0;\"><span style=\"font-size: 16px;\">{{CLIENT CONCEPT}}</span></p>\n");
    stringBuffer.append(
      "                                            <p style=\"line-height: 28px; text-align: left; font-size: 12px; margin: 0;\"><span style=\"font-size: 16px;\">On their {{CLIENT LOCATION}}</span></p>\n");
    stringBuffer.append("                                        </div>\n");
    stringBuffer.append("                                    </div>\n");
    stringBuffer
      .append("                                    <!--[if mso]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 0px; padding-bottom: 0px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\n");
    stringBuffer.append(
      "                                    <div style=\"color:#202431;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;line-height:150%;padding-top:0px;padding-right:10px;padding-bottom:0px;padding-left:10px;\">\n");
    stringBuffer.append(
      "                                        <div style=\"font-size: 12px; line-height: 18px; font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; color: #202431;\">\n");
    stringBuffer.append(
      "                                            <p style=\"font-size: 14px; line-height: 45px; text-align: left; margin: 0;\"><span style=\"font-size: 30px;\">Size.</span></p>\n");
    stringBuffer.append("                                        </div>\n");
    stringBuffer.append("                                    </div>\n");
    stringBuffer
      .append("                                    <!--[if mso]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 5px; padding-bottom: 15px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\n");
    stringBuffer.append(
      "                                    <div style=\"color:#202431;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;line-height:180%;padding-top:5px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n");
    stringBuffer.append(
      "                                        <div style=\"font-size: 12px; line-height: 21px; font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; color: #202431;\">\n");
    stringBuffer.append(
      "                                            <p style=\"font-size: 14px; line-height: 28px; text-align: left; margin: 0;\"><span style=\"font-size: 16px;\">About {{CLIENT SIZE}}</span></p>\n");
    stringBuffer.append("                                        </div>\n");
    stringBuffer.append("                                    </div>\n");
    stringBuffer
      .append("                                    <!--[if mso]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 0px; padding-bottom: 0px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\n");
    stringBuffer.append(
      "                                    <div style=\"color:#202431;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;line-height:150%;padding-top:0px;padding-right:10px;padding-bottom:0px;padding-left:10px;\">\n");
    stringBuffer.append(
      "                                        <div style=\"font-size: 12px; line-height: 18px; font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; color: #202431;\">\n");
    stringBuffer.append(
      "                                            <p style=\"font-size: 14px; line-height: 45px; text-align: left; margin: 0;\"><span style=\"font-size: 30px;\">Availability.</span></p>\n");
    stringBuffer.append("                                        </div>\n");
    stringBuffer.append("                                    </div>\n");
    stringBuffer
      .append("                                    <!--[if mso]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 5px; padding-bottom: 15px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\n");
    stringBuffer.append(
      "                                    <div style=\"color:#202431;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;line-height:180%;padding-top:5px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n");
    stringBuffer.append(
      "                                        <div style=\"font-size: 12px; line-height: 21px; font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; color: #202431;\">\n");
    stringBuffer.append(
      "                                            <p style=\"font-size: 14px; line-height: 28px; text-align: left; margin: 0;\"><span style=\"font-size: 16px;\">{{CLIENT AVAILABILITY}}</span></p>\n");
    stringBuffer.append("                                        </div>\n");
    stringBuffer.append("                                    </div>\n");
    stringBuffer
      .append("                                    <!--[if mso]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 0px; padding-bottom: 0px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\n");
    stringBuffer.append(
      "                                    <div style=\"color:#202431;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;line-height:150%;padding-top:0px;padding-right:10px;padding-bottom:0px;padding-left:10px;\">\n");
    stringBuffer.append(
      "                                        <div style=\"font-size: 12px; line-height: 18px; font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; color: #202431;\">\n");
    stringBuffer.append(
      "                                            <p style=\"font-size: 14px; line-height: 45px; text-align: left; margin: 0;\"><span style=\"font-size: 30px;\">Contact.</span></p>\n");
    stringBuffer.append("                                        </div>\n");
    stringBuffer.append("                                    </div>\n");
    stringBuffer
      .append("                                    <!--[if mso]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 5px; padding-bottom: 15px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\n");
    stringBuffer.append(
      "                                    <div style=\"color:#F4F4F4;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;line-height:180%;padding-top:5px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n");
    stringBuffer.append(
      "                                        <div style=\"font-size: 12px; line-height: 21px; font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; color: #F4F4F4;\">\n");
    stringBuffer.append(
      "                                            <p style=\"font-size: 14px; line-height: 36px; text-align: left; margin: 0;\"><span style=\"font-size: 20px; color: #202431;\">{{CLIENT EMAIL}}</span></p>\n");
    stringBuffer.append("                                        </div>\n");
    stringBuffer.append("                                    </div>\n");
    stringBuffer
      .append("                                    <!--[if mso]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 5px; padding-bottom: 10px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\n");
    stringBuffer.append(
      "                                    <div style=\"color:#28404F;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;line-height:120%;padding-top:5px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n");
    stringBuffer.append(
      "                                        <div style=\"font-size: 12px; line-height: 14px; font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; color: #28404F;\">\n");
    stringBuffer.append(
      "                                            <p style=\"font-size: 14px; line-height: 36px; margin: 0;\"><span style=\"color: #202431; font-size: 30px;\"><strong><span style=\"line-height: 36px; font-size: 30px;\">Inspiration.</span></strong></span></p>\n");
    stringBuffer.append("                                        </div>\n");
    stringBuffer.append("                                    </div>\n");
    stringBuffer
      .append("                                    <!--[if mso]></td></tr></table><![endif]-->\n");
    stringBuffer.append("                                    <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer.append("                                </div>\n");
    stringBuffer.append("                                <!--<![endif]-->\n");
    stringBuffer.append("                            </div>\n");
    stringBuffer.append("                        </div>\n");
    stringBuffer
      .append("                        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n");
    stringBuffer.append("                    </div>\n");
    stringBuffer.append("                </div>\n");
    stringBuffer.append("            </div>\n");
    stringBuffer.append("            <div style=\"background-color:#f6f6f4;\">\n");
    stringBuffer.append(
      "                <div class=\"block-grid two-up\" style=\"Margin: 0 auto; min-width: 320px; max-width: 745px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: #FFFFFF;;\">\n");
    stringBuffer.append(
      "                    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#FFFFFF;\">\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#f6f6f4;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:745px\"><tr class=\"layout-full-width\" style=\"background-color:#FFFFFF\"><![endif]-->\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]><td align=\"center\" width=\"372\" style=\"background-color:#FFFFFF;width:372px; border-top: 7px solid #f6f6f4; border-left: 7px solid #f6f6f4; border-bottom: 7px solid #f6f6f4; border-right: 7px solid #f6f6f4;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 25px; padding-left: 25px; padding-top:25px; padding-bottom:25px;\"><![endif]-->\n");
    stringBuffer.append(
      "                        <div class=\"col num6\" style=\"min-width: 320px; max-width: 372px; display: table-cell; vertical-align: top;;\">\n");
    stringBuffer.append("                            <div style=\"width:100% !important;\">\n");
    stringBuffer.append("                                <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer.append(
      "                                <div style=\"border-top:7px solid #f6f6f4; border-left:7px solid #f6f6f4; border-bottom:7px solid #f6f6f4; border-right:7px solid #f6f6f4; padding-top:25px; padding-bottom:25px; padding-right: 25px; padding-left: 25px;\">\n");
    stringBuffer.append("                                    <!--<![endif]-->\n");
    stringBuffer.append(
      "                                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 0px; padding-bottom: 5px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\n");
    stringBuffer.append(
      "                                    <div style=\"color:#2bbbb2;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;line-height:120%;padding-top:0px;padding-right:10px;padding-bottom:5px;padding-left:10px;\">\n");
    stringBuffer.append(
      "                                        <div style=\"line-height: 14px; font-size: 12px; font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; color: #2bbbb2;\">\n");
    stringBuffer.append(
      "                                            <p style=\"line-height: 21px; font-size: 12px; margin: 0;\"><span style=\"font-size: 18px; color: #202431;\"><strong>{{INSPIRATION DESC 1}}</strong></span></p>\n");
    stringBuffer.append(
      "                                            <img align=\"center\" alt=\"Image\"\n");
    stringBuffer.append("                                                 border=\"0\"\n");
    stringBuffer.append(
      "                                                 class=\"center fixedwidth\" src=\"{{INSPIRATION IMGURL 1}}\" style=\"outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; clear: both; border: 0; height: auto; float: none; width: 100%; display: block;\" title=\"Image\"/>\n");
    stringBuffer.append("\n");
    stringBuffer.append("                                        </div>\n");
    stringBuffer.append("                                    </div>\n");
    stringBuffer
      .append("                                    <!--[if mso]></td></tr></table><![endif]-->\n");
    stringBuffer.append("                                </div>\n");
    stringBuffer.append("                                <!--<![endif]-->\n");
    stringBuffer.append("                            </div>\n");
    stringBuffer.append("                        </div>\n");
    stringBuffer
      .append("                        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]></td><td align=\"center\" width=\"372\" style=\"background-color:#FFFFFF;width:372px; border-top: 7px solid #f6f6f4; border-left: 7px solid #f6f6f4; border-bottom: 7px solid #f6f6f4; border-right: 7px solid #f6f6f4;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 25px; padding-left: 25px; padding-top:25px; padding-bottom:25px;\"><![endif]-->\n");
    stringBuffer.append(
      "                        <div class=\"col num6\" style=\"min-width: 320px; max-width: 372px; display: table-cell; vertical-align: top;;\">\n");
    stringBuffer.append("                            <div style=\"width:100% !important;\">\n");
    stringBuffer.append("                                <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer.append(
      "                                <div style=\"border-top:7px solid #f6f6f4; border-left:7px solid #f6f6f4; border-bottom:7px solid #f6f6f4; border-right:7px solid #f6f6f4; padding-top:25px; padding-bottom:25px; padding-right: 25px; padding-left: 25px;\">\n");
    stringBuffer.append("                                    <!--<![endif]-->\n");
    stringBuffer.append(
      "                                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 0px; padding-bottom: 5px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\n");
    stringBuffer.append(
      "                                    <div style=\"color:#2bbbb2;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;line-height:120%;padding-top:0px;padding-right:10px;padding-bottom:5px;padding-left:10px;\">\n");
    stringBuffer.append(
      "                                        <div style=\"font-size: 12px; line-height: 14px; font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; color: #2bbbb2;\">\n");
    stringBuffer.append(
      "                                            <p style=\"font-size: 14px; line-height: 21px; margin: 0;\"><span style=\"font-size: 18px; color: #202431;\"><strong>{{INSPIRATION DESC 2}}</strong></span></p>\n");
    stringBuffer.append(
      "                                            <img align=\"center\" alt=\"Image\"\n");
    stringBuffer.append("                                                 border=\"0\"\n");
    stringBuffer.append(
      "                                                 class=\"center fixedwidth\" src=\"{{INSPIRATION IMGURL 2}}\" style=\"outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; clear: both; border: 0; height: auto; float: none; width: 100%; display: block;\" title=\"Image\"/>\n");
    stringBuffer.append("\n");
    stringBuffer.append("                                        </div>\n");
    stringBuffer.append("                                    </div>\n");
    stringBuffer
      .append("                                    <!--[if mso]></td></tr></table><![endif]-->\n");
    stringBuffer.append("                                </div>\n");
    stringBuffer.append("                                <!--<![endif]-->\n");
    stringBuffer.append("                            </div>\n");
    stringBuffer.append("                        </div>\n");
    stringBuffer
      .append("                        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n");
    stringBuffer.append("                    </div>\n");
    stringBuffer.append("                </div>\n");
    stringBuffer.append("            </div>\n");
    stringBuffer.append("            <div style=\"background-color:#f6f6f4;\">\n");
    stringBuffer.append(
      "                <div class=\"block-grid two-up\" style=\"Margin: 0 auto; min-width: 320px; max-width: 745px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: #FFFFFF;;\">\n");
    stringBuffer.append(
      "                    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#FFFFFF;\">\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#f6f6f4;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:745px\"><tr class=\"layout-full-width\" style=\"background-color:#FFFFFF\"><![endif]-->\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]><td align=\"center\" width=\"372\" style=\"background-color:#FFFFFF;width:372px; border-top: 7px solid #f6f6f4; border-left: 7px solid #f6f6f4; border-bottom: 7px solid #f6f6f4; border-right: 7px solid #f6f6f4;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 25px; padding-left: 25px; padding-top:25px; padding-bottom:25px;\"><![endif]-->\n");
    stringBuffer.append(
      "                        <div class=\"col num6\" style=\"min-width: 320px; max-width: 372px; display: table-cell; vertical-align: top;;\">\n");
    stringBuffer.append("                            <div style=\"width:100% !important;\">\n");
    stringBuffer.append("                                <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer.append(
      "                                <div style=\"border-top:7px solid #f6f6f4; border-left:7px solid #f6f6f4; border-bottom:7px solid #f6f6f4; border-right:7px solid #f6f6f4; padding-top:25px; padding-bottom:25px; padding-right: 25px; padding-left: 25px;\">\n");
    stringBuffer.append("                                    <!--<![endif]-->\n");
    stringBuffer.append(
      "                                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 0px; padding-bottom: 5px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\n");
    stringBuffer.append(
      "                                    <div style=\"color:#2bbbb2;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;line-height:120%;padding-top:0px;padding-right:10px;padding-bottom:5px;padding-left:10px;\">\n");
    stringBuffer.append(
      "                                        <div style=\"line-height: 14px; font-size: 12px; font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; color: #2bbbb2;\">\n");
    stringBuffer.append(
      "                                            <p style=\"line-height: 21px; font-size: 12px; margin: 0;\"><span style=\"font-size: 18px; color: #202431;\"><strong>{{INSPIRATION DESC 3}}</strong></span></p>\n");
    stringBuffer.append(
      "                                            <img align=\"center\" alt=\"Image\"\n");
    stringBuffer.append("                                                 border=\"0\"\n");
    stringBuffer.append(
      "                                                 class=\"center fixedwidth\" src=\"{{INSPIRATION IMGURL 3}}\" style=\"outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; clear: both; border: 0; height: auto; float: none; width: 100%; display: block;\" title=\"Image\"/>\n");
    stringBuffer.append("                                        </div>\n");
    stringBuffer.append("                                    </div>\n");
    stringBuffer
      .append("                                    <!--[if mso]></td></tr></table><![endif]-->\n");
    stringBuffer.append("                                </div>\n");
    stringBuffer.append("                                <!--<![endif]-->\n");
    stringBuffer.append("                            </div>\n");
    stringBuffer.append("                        </div>\n");
    stringBuffer
      .append("                        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]></td><td align=\"center\" width=\"372\" style=\"background-color:#FFFFFF;width:372px; border-top: 7px solid #f6f6f4; border-left: 7px solid #f6f6f4; border-bottom: 7px solid #f6f6f4; border-right: 7px solid #f6f6f4;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 25px; padding-left: 25px; padding-top:25px; padding-bottom:25px;\"><![endif]-->\n");
    stringBuffer.append(
      "                        <div class=\"col num6\" style=\"min-width: 320px; max-width: 372px; display: table-cell; vertical-align: top;;\">\n");
    stringBuffer.append("                            <div style=\"width:100% !important;\">\n");
    stringBuffer.append("                                <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer.append(
      "                                <div style=\"border-top:7px solid #f6f6f4; border-left:7px solid #f6f6f4; border-bottom:7px solid #f6f6f4; border-right:7px solid #f6f6f4; padding-top:25px; padding-bottom:25px; padding-right: 25px; padding-left: 25px;\">\n");
    stringBuffer.append("                                    <!--<![endif]-->\n");
    stringBuffer.append(
      "                                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 0px; padding-bottom: 5px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\n");
    stringBuffer.append(
      "                                    <div style=\"color:#2bbbb2;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;line-height:120%;padding-top:0px;padding-right:10px;padding-bottom:5px;padding-left:10px;\">\n");
    stringBuffer.append(
      "                                        <div style=\"font-size: 12px; line-height: 14px; font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; color: #2bbbb2;\">\n");
    stringBuffer.append(
      "                                            <p style=\"font-size: 14px; line-height: 21px; margin: 0;\"><span style=\"font-size: 18px; color: #202431;\"><strong>{{INSPIRATION DESC 4}}</strong></span></p>\n");
    stringBuffer.append(
      "                                            <img align=\"center\" alt=\"Image\"\n");
    stringBuffer.append(
      "                                                 border=\"0\" class=\"center fixedwidth\" src=\"{{INSPIRATION IMGURL 4}}\" style=\"outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; clear: both; border: 0; height: auto; float: none; width: 100%; display: block;\" title=\"Image\"/>\n");
    stringBuffer.append("                                        </div>\n");
    stringBuffer.append("                                    </div>\n");
    stringBuffer
      .append("                                    <!--[if mso]></td></tr></table><![endif]-->\n");
    stringBuffer.append("                                </div>\n");
    stringBuffer.append("                                <!--<![endif]-->\n");
    stringBuffer.append("                            </div>\n");
    stringBuffer.append("                        </div>\n");
    stringBuffer
      .append("                        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n");
    stringBuffer.append("                    </div>\n");
    stringBuffer.append("                </div>\n");
    stringBuffer.append("            </div>\n");
    stringBuffer.append("            <div style=\"background-color:#F6F6F4;\">\n");
    stringBuffer.append(
      "                <div class=\"block-grid\" style=\"Margin: 0 auto; min-width: 320px; max-width: 745px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: transparent;;\">\n");
    stringBuffer.append(
      "                    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#F6F6F4;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:745px\"><tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]><td align=\"center\" width=\"745\" style=\"background-color:transparent;width:745px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\n");
    stringBuffer.append(
      "                        <div class=\"col num12\" style=\"min-width: 320px; max-width: 745px; display: table-cell; vertical-align: top;;\">\n");
    stringBuffer.append("                            <div style=\"width:100% !important;\">\n");
    stringBuffer.append("                                <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer.append(
      "                                <div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n");
    stringBuffer.append("                                    <!--<![endif]-->\n");
    stringBuffer.append(
      "                                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 5px; padding-bottom: 10px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\n");
    stringBuffer.append(
      "                                    <div style=\"color:#28404F;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;line-height:120%;padding-top:5px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n");
    stringBuffer.append(
      "                                        <div style=\"font-size: 12px; line-height: 14px; font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; color: #28404F;\">\n");
    stringBuffer.append(
      "                                            <p style=\"font-size: 14px; line-height: 16px; margin: 0;\"><span style=\"color: #202431; font-size: 14px; line-height: 16px;\"><strong><span style=\"font-size: 38px; line-height: 45px;\">Ready to accept?</span></strong></span></p>\n");
    stringBuffer.append("                                        </div>\n");
    stringBuffer.append("                                    </div>\n");
    stringBuffer
      .append("                                    <!--[if mso]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\n");
    stringBuffer.append(
      "                                    <div style=\"color:#555555;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;line-height:120%;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n");
    stringBuffer.append(
      "                                        <div style=\"font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; font-size: 12px; line-height: 14px; color: #555555;\">\n");
    stringBuffer.append(
      "                                            <p style=\"font-size: 14px; line-height: 16px; margin: 0;\"><span style=\"color: #202431; font-size: 14px; line-height: 16px;\">If you want to accept this request, please fill in the form below!</span></p><br>\n");
    stringBuffer.append(
      "                                            <form action=\"http://inkstep-backend.eu-west-2.elasticbeanstalk.com/journey/208\"\n");
    stringBuffer.append("                                                  method=\"POST\">\n");
    stringBuffer.append(
      "                                                <div style=\"background-color:#f6f6f4;\">\n");
    stringBuffer.append(
      "                                                    <div class=\"block-grid two-up\" style=\"Margin: 0 auto; min-width: 320px; max-width: 745px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: #FFFFFF;;\">\n");
    stringBuffer.append(
      "                                                        <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#FFFFFF;\">\n");
    stringBuffer.append(
      "                                                            <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#f6f6f4;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:745px\"><tr class=\"layout-full-width\" style=\"background-color:#FFFFFF\"><![endif]-->\n");
    stringBuffer.append(
      "                                                            <!--[if (mso)|(IE)]><td align=\"center\" width=\"372\" style=\"background-color:#FFFFFF;width:372px; border-top: 7px solid #f6f6f4; border-left: 7px solid #f6f6f4; border-bottom: 7px solid #f6f6f4; border-right: 7px solid #f6f6f4;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 25px; padding-left: 25px; padding-top:25px; padding-bottom:25px;\"><![endif]-->\n");
    stringBuffer.append(
      "                                                            <div class=\"col num6\" style=\"min-width: 320px; max-width: 372px; display: table-cell; vertical-align: top;;\">\n");
    stringBuffer.append(
      "                                                                <div style=\"width:100% !important;\">\n");
    stringBuffer.append(
      "                                                                    <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer.append(
      "                                                                    <div style=\"border-top:7px solid #f6f6f4; border-left:7px solid #f6f6f4; border-bottom:7px solid #f6f6f4; border-right:7px solid #f6f6f4; padding-top:25px; padding-bottom:25px; padding-right: 25px; padding-left: 25px;\">\n");
    stringBuffer.append(
      "                                                                        <!--<![endif]-->\n");
    stringBuffer.append(
      "                                                                        <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 0px; padding-bottom: 5px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\n");
    stringBuffer.append(
      "                                                                        <div style=\"color:#2bbbb2;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;line-height:120%;padding-top:0px;padding-right:10px;padding-bottom:5px;padding-left:10px;\">\n");
    stringBuffer.append(
      "                                                                            <div style=\"line-height: 14px; font-size: 12px; font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; color: #2bbbb2;\">\n");
    stringBuffer.append(
      "                                                                                <p style=\"font-size: 14px; line-height: 21px; margin: 0;\"><span style=\"font-size: 18px; color: #202431;\"><strong>Quote Lower Bound (e.g. $600)</strong></span></p>\n");
    stringBuffer.append(
      "                                                                                <input type=\"text\" name=\"quote_lower\"><br>\n");
    stringBuffer.append(
      "                                                                            </div>\n");
    stringBuffer
      .append("                                                                        </div>\n");
    stringBuffer.append(
      "                                                                        <!--[if mso]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                                                                        <div></div>\n");
    stringBuffer.append(
      "                                                                        <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer
      .append("                                                                    </div>\n");
    stringBuffer.append(
      "                                                                    <!--<![endif]-->\n");
    stringBuffer.append("                                                                </div>\n");
    stringBuffer.append("                                                            </div>\n");
    stringBuffer.append(
      "                                                            <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                                                            <!--[if (mso)|(IE)]></td><td align=\"center\" width=\"372\" style=\"background-color:#FFFFFF;width:372px; border-top: 7px solid #f6f6f4; border-left: 7px solid #f6f6f4; border-bottom: 7px solid #f6f6f4; border-right: 7px solid #f6f6f4;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 25px; padding-left: 25px; padding-top:25px; padding-bottom:25px;\"><![endif]-->\n");
    stringBuffer.append(
      "                                                            <div class=\"col num6\" style=\"min-width: 320px; max-width: 372px; display: table-cell; vertical-align: top;;\">\n");
    stringBuffer.append(
      "                                                                <div style=\"width:100% !important;\">\n");
    stringBuffer.append(
      "                                                                    <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer.append(
      "                                                                    <div style=\"border-top:7px solid #f6f6f4; border-left:7px solid #f6f6f4; border-bottom:7px solid #f6f6f4; border-right:7px solid #f6f6f4; padding-top:25px; padding-bottom:25px; padding-right: 25px; padding-left: 25px;\">\n");
    stringBuffer.append(
      "                                                                        <!--<![endif]-->\n");
    stringBuffer.append(
      "                                                                        <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 0px; padding-bottom: 5px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\n");
    stringBuffer.append(
      "                                                                        <div style=\"color:#2bbbb2;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;line-height:120%;padding-top:0px;padding-right:10px;padding-bottom:5px;padding-left:10px;\">\n");
    stringBuffer.append(
      "                                                                            <div style=\"font-size: 12px; line-height: 14px; font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; color: #2bbbb2;\">\n");
    stringBuffer.append(
      "                                                                                <p style=\"font-size: 14px; line-height: 21px; margin: 0;\"><span style=\"font-size: 18px; color: #202431;\"><strong>Quote Upper Bound (e.g. $620)</strong></span></p>\n");
    stringBuffer.append(
      "                                                                                <input type=\"text\" name=\"quote_upper\"><br>\n");
    stringBuffer.append(
      "                                                                            </div>\n");
    stringBuffer
      .append("                                                                        </div>\n");
    stringBuffer.append(
      "                                                                        <!--[if mso]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                                                                        <div></div>\n");
    stringBuffer.append(
      "                                                                        <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer
      .append("                                                                    </div>\n");
    stringBuffer.append(
      "                                                                    <!--<![endif]-->\n");
    stringBuffer.append("                                                                </div>\n");
    stringBuffer.append("                                                            </div>\n");
    stringBuffer.append(
      "                                                            <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                                                            <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n");
    stringBuffer.append("                                                        </div>\n");
    stringBuffer.append("                                                    </div>\n");
    stringBuffer.append("                                                </div>\n");
    stringBuffer.append(
      "                                                <div style=\"background-color:#f6f6f4;\">\n");
    stringBuffer.append(
      "                                                    <div class=\"block-grid two-up\" style=\"Margin: 0 auto; min-width: 320px; max-width: 745px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: #FFFFFF;;\">\n");
    stringBuffer.append(
      "                                                        <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#FFFFFF;\">\n");
    stringBuffer.append(
      "                                                            <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#f6f6f4;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:745px\"><tr class=\"layout-full-width\" style=\"background-color:#FFFFFF\"><![endif]-->\n");
    stringBuffer.append(
      "                                                            <!--[if (mso)|(IE)]><td align=\"center\" width=\"372\" style=\"background-color:#FFFFFF;width:372px; border-top: 7px solid #f6f6f4; border-left: 7px solid #f6f6f4; border-bottom: 7px solid #f6f6f4; border-right: 7px solid #f6f6f4;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 25px; padding-left: 25px; padding-top:25px; padding-bottom:25px;\"><![endif]-->\n");
    stringBuffer.append(
      "                                                            <div class=\"col num6\" style=\"min-width: 320px; max-width: 372px; display: table-cell; vertical-align: top;;\">\n");
    stringBuffer.append(
      "                                                                <div style=\"width:100% !important;\">\n");
    stringBuffer.append(
      "                                                                    <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer.append(
      "                                                                    <div style=\"border-top:7px solid #f6f6f4; border-left:7px solid #f6f6f4; border-bottom:7px solid #f6f6f4; border-right:7px solid #f6f6f4; padding-top:25px; padding-bottom:25px; padding-right: 25px; padding-left: 25px;\">\n");
    stringBuffer.append(
      "                                                                        <!--<![endif]-->\n");
    stringBuffer.append(
      "                                                                        <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 0px; padding-bottom: 5px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\n");
    stringBuffer.append(
      "                                                                        <div style=\"color:#2bbbb2;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;line-height:120%;padding-top:0px;padding-right:10px;padding-bottom:5px;padding-left:10px;\">\n");
    stringBuffer.append(
      "                                                                            <div style=\"line-height: 14px; font-size: 12px; font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; color: #2bbbb2;\">\n");
    stringBuffer.append(
      "                                                                                <center>\n");
    stringBuffer.append(
      "                                                                                    <p style=\"font-size: 14px; line-height: 21px; margin: 0;\"><span style=\"font-size: 18px; color: #202431;\"><strong>Booking Date (e.g. 19/09/2019)</strong></span></p>\n");
    stringBuffer.append(
      "                                                                                    <input type=\"date\" name=\"booking_date\"><br>\n");
    stringBuffer.append(
      "                                                                                </center>\n");
    stringBuffer.append(
      "                                                                            </div>\n");
    stringBuffer
      .append("                                                                        </div>\n");
    stringBuffer.append(
      "                                                                        <!--[if mso]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                                                                        <div></div>\n");
    stringBuffer.append(
      "                                                                        <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer
      .append("                                                                    </div>\n");
    stringBuffer.append(
      "                                                                    <!--<![endif]-->\n");
    stringBuffer.append("                                                                </div>\n");
    stringBuffer.append("                                                            </div>\n");
    stringBuffer.append(
      "                                                            <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                                                            <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                                                            <div class=\"col num6\" style=\"min-width: 320px; max-width: 372px; display: table-cell; vertical-align: top;;\">\n");
    stringBuffer.append(
      "                                                                <div style=\"width:100% !important;\">\n");
    stringBuffer.append(
      "                                                                    <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer.append(
      "                                                                    <div style=\"border-top:7px solid #f6f6f4; border-left:7px solid #f6f6f4; border-bottom:7px solid #f6f6f4; border-right:7px solid #f6f6f4; padding-top:25px; padding-bottom:25px; padding-right: 25px; padding-left: 25px;\">\n");
    stringBuffer.append(
      "                                                                        <!--<![endif]-->\n");
    stringBuffer.append(
      "                                                                        <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 0px; padding-bottom: 5px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\n");
    stringBuffer.append(
      "                                                                        <div style=\"color:#2bbbb2;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;line-height:120%;padding-top:0px;padding-right:10px;padding-bottom:5px;padding-left:10px;\">\n");
    stringBuffer.append(
      "                                                                            <div style=\"line-height: 14px; font-size: 12px; font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; color: #2bbbb2;\">\n");
    stringBuffer.append(
      "                                                                                <center>\n");
    stringBuffer.append(
      "                                                                                    <p style=\"font-size: 14px; line-height: 21px; margin: 0;\"><span style=\"font-size: 18px; color: #202431;\"><strong>Booking Time (e.g. 13:00)</strong></span></p>\n");
    stringBuffer.append(
      "                                                                                    <input type=\"time\" name=\"booking_time\"><br>\n");
    stringBuffer.append(
      "                                                                                </center>\n");
    stringBuffer.append(
      "                                                                            </div>\n");
    stringBuffer
      .append("                                                                        </div>\n");
    stringBuffer.append(
      "                                                                        <!--[if mso]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                                                                        <div></div>\n");
    stringBuffer.append(
      "                                                                        <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer
      .append("                                                                    </div>\n");
    stringBuffer.append(
      "                                                                    <!--<![endif]-->\n");
    stringBuffer.append("                                                                </div>\n");
    stringBuffer.append("                                                            </div>\n");
    stringBuffer.append(
      "                                                            <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                                                            <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n");
    stringBuffer.append("                                                        </div>\n");
    stringBuffer.append("                                                    </div>\n");
    stringBuffer.append("                                                </div>\n");
    stringBuffer
      .append("                                                <input type=\"submit\"\n");
    stringBuffer
      .append("                                                       value=\"Accept!\">\n");
    stringBuffer.append("                                            </form>\n");
    stringBuffer.append("                                        </div>\n");
    stringBuffer.append("                                    </div>\n");
    stringBuffer
      .append("                                    <!--[if mso]></td></tr></table><![endif]-->\n");
    stringBuffer.append("                                    <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer.append("                                </div>\n");
    stringBuffer.append("                                <!--<![endif]-->\n");
    stringBuffer.append("                            </div>\n");
    stringBuffer.append("                        </div>\n");
    stringBuffer
      .append("                        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n");
    stringBuffer.append("                    </div>\n");
    stringBuffer.append("                </div>\n");
    stringBuffer.append("            </div>\n");
    stringBuffer.append("            <div style=\"background-color:transparent;\">\n");
    stringBuffer.append(
      "                <div class=\"block-grid\" style=\"Margin: 0 auto; min-width: 320px; max-width: 745px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: transparent;;\">\n");
    stringBuffer.append(
      "                    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:745px\"><tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]><td align=\"center\" width=\"745\" style=\"background-color:transparent;width:745px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:15px; padding-bottom:15px;\"><![endif]-->\n");
    stringBuffer.append(
      "                        <div class=\"col num12\" style=\"min-width: 320px; max-width: 745px; display: table-cell; vertical-align: top;;\">\n");
    stringBuffer.append("                            <div style=\"width:100% !important;\">\n");
    stringBuffer.append("                                <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer.append(
      "                                <div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:15px; padding-bottom:15px; padding-right: 0px; padding-left: 0px;\">\n");
    stringBuffer.append("                                    <!--<![endif]-->\n");
    stringBuffer.append(
      "                                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->\n");
    stringBuffer.append(
      "                                    <div style=\"color:#28404F;font-family:'Roboto', Tahoma, Verdana, Segoe, sans-serif;line-height:120%;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n");
    stringBuffer.append(
      "                                        <div style=\"font-size: 12px; line-height: 14px; font-family: 'Roboto', Tahoma, Verdana, Segoe, sans-serif; color: #28404F;\">\n");
    stringBuffer.append(
      "                                            <p style=\"font-size: 14px; line-height: 16px; margin: 0;\"><span style=\"color: #ffffff;\">Sent from inkstep, on behalf of {{CLIENT NAME}} for {{ARTIST NAME}} {{STUDIO NAME}}</span></p>\n");
    stringBuffer.append("                                        </div>\n");
    stringBuffer.append("                                    </div>\n");
    stringBuffer
      .append("                                    <!--[if mso]></td></tr></table><![endif]-->\n");
    stringBuffer.append("                                    <!--[if (!mso)&(!IE)]><!-->\n");
    stringBuffer.append("                                </div>\n");
    stringBuffer.append("                                <!--<![endif]-->\n");
    stringBuffer.append("                            </div>\n");
    stringBuffer.append("                        </div>\n");
    stringBuffer
      .append("                        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n");
    stringBuffer.append(
      "                        <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n");
    stringBuffer.append("                    </div>\n");
    stringBuffer.append("                </div>\n");
    stringBuffer.append("            </div>\n");
    stringBuffer.append("            <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n");
    stringBuffer.append("        </td>\n");
    stringBuffer.append("    </tr>\n");
    stringBuffer.append("    </tbody>\n");
    stringBuffer.append("</table>\n");
    stringBuffer.append("<!--[if (IE)]></div><![endif]-->\n");
    stringBuffer.append("</body>\n");
    stringBuffer.append("</html>\n");

    return stringBuffer.toString();
  }
}
