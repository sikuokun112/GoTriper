package com.gogotriper.gotriper.controllers;

import com.gogotriper.gotriper.entity.*;
import com.gogotriper.gotriper.services.*;
import com.gogotriper.gotriper.utils.EncrytedPasswordUtils;
import com.gogotriper.gotriper.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private DanhMucService danhMucService;

    @Autowired
    private BaiDangService baiDangService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private DiaDiemService diaDiemService;

    @Autowired
    private BinhLuanService binhLuanService;

    @Autowired
    private TinhThanhService tinhThanhService;

    @Autowired
    private VanDeService vanDeService;

    // Do du lieu len thanh header
    @ModelAttribute("danhmuclist")
    public List<DanhMuc> getAllDanhMuc(){
        return danhMucService.getAllDanhMuc();
    }

    @ModelAttribute("tinhthanhlist")
    public List<TinhThanh> getAllTinhThanh(){
        return tinhThanhService.getAllTinhThanh();
    }

    @ModelAttribute("avatar")
    public String getAccountAvatar(Principal principal){

        if(principal !=null){
            String userName = principal.getName();
            Account user = userService.findByUserName(userName);
            if(user.getListUserImages().size()!=0){
                return "getimage/"+user.getListUserImages().get(user.getListUserImages().size()-1).getImageUrl();
            }
        }
        return "/mainpage/images/featured1.jpg";
    }


    @RequestMapping(value = "/listdanhmuc", method = RequestMethod.GET)
    public String ShowListDanhMuc(Model model) {

        model.addAttribute("danhmuclist",danhMucService.getAllDanhMuc());
        BaiDang baiDang = baiDangService.findBaiDangById(2);
        List<Image> img = imageService.findAllImageByBaiDang(baiDang);
        model.addAttribute("img",img);
        return "test_danhmuc";
    }

    @PostMapping("/savebaidang")
    public String saveBaiDang(@RequestParam("tieude") String tieude , @RequestParam("noidung") String noidung, @RequestParam("danhmuc") String danhmuc, @RequestParam("photo")List<MultipartFile> photos,Principal principal){
        System.out.println(danhMucService.findDanhMucByName(danhmuc).getTenDanhMuc());
        BaiDang baiDang = new BaiDang();
        baiDang.setDanhMuc(danhMucService.findDanhMucByName(danhmuc));
        baiDang.setNoiDung(noidung);
        baiDang.setTieuDe(tieude);
        // set ngay dang now
        Date now = new Date();
        Timestamp ts = new Timestamp(now.getTime());
        baiDang.setThoiGianDang(ts);
        // set ngay dang them 10 ngay
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.DATE,10);
        Date end = c.getTime();
        Timestamp ts2 = new Timestamp(end.getTime());
        System.out.println("TG DANG "+ ts2);
        baiDang.setThoiGianHetHan(ts2);

        baiDang.setSoLuotXem(1);
        baiDang.setFlag(0);

        String userName = principal.getName();
        Account user  = userService.findByUserName(userName);
        baiDang.setUserId(user);
        List<Image > images = new ArrayList<>();
        int i =0;
        for(MultipartFile photo : photos){
                Image image = new Image();
                i++;
                int series = imageService.getLatestIdImage()+i;
                image.setImageUrl(series+"_"+photo.getOriginalFilename());
                image.setBaiDangImage(baiDang);
                images.add(image);
                imageService.saveImageToUploads(photo,series);
        }
        baiDang.setListImage_BaiDang(images);
        baiDangService.saveBaiDang(baiDang);
        return "success";
    }

    @PostMapping("/diadiem/{id}/savebaidang2")
    public String saveBaiDang2(@RequestParam("tieude") String tieude , @RequestParam("noidung") String noidung, @RequestParam("danhmucname") String danhmucname, @RequestParam("pro-image")List<MultipartFile> photos,Principal principal,@PathVariable String id){
        BaiDang baiDang = new BaiDang();
        baiDang.setDanhMuc(danhMucService.findDanhMucById(Integer.parseInt(danhmucname)));
        baiDang.setDiaDiem(diaDiemService.findDiaDiemById(Integer.parseInt(id)));
        baiDang.setNoiDung(noidung);
        baiDang.setTieuDe(tieude);
        String userName = principal.getName();
        Account user  = userService.findByUserName(userName);
        baiDang.setUserId(user);
        // set ngay dang now

        Date now = new Date();
        Timestamp ts = new Timestamp(now.getTime());
        baiDang.setThoiGianDang(ts);
        // set ngay dang them 2 ngay
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.DATE,10);
        Date end = c.getTime();
        Timestamp ts2 = new Timestamp(end.getTime());
        baiDang.setThoiGianHetHan(ts2);
        baiDang.setFlag(0);

        List<Image > images = new ArrayList<>();
        int i =0;
        for(MultipartFile photo : photos){
            Image image = new Image();
            i++;
            int series = imageService.getLatestIdImage()+i;
            image.setImageUrl(series+"_"+photo.getOriginalFilename());
            image.setBaiDangImage(baiDang);
            images.add(image);
            imageService.saveImageToUploads(photo,series);
        }
        baiDang.setListImage_BaiDang(images);
        baiDangService.saveBaiDang(baiDang);
        return "success";
    }

    // vande
    @RequestMapping(value = "/vande", method = RequestMethod.GET)
    public String vanDe() {

        return "layouts/vanDe";
    }

    @PostMapping("/savevande")
    public String saveVanDe(@RequestParam("tieude") String tieude,@RequestParam("noidung") String noidung, Principal principal){

        Account account = userService.findByUserName(principal.getName());
        VanDe vanDe = new VanDe();
        vanDe.setTieude(tieude);
        vanDe.setNoidung(noidung);
        vanDe.setFlag(0);
        vanDe.setUserIdVanDe(account);

        vanDeService.saveVanDe(vanDe);
        return "redirect:/vande";

    }

    // main
    @RequestMapping(value = {"/home","/"},method =  RequestMethod.GET)
    public String HomeMain(Model model){
        List<BaiDang> top3views = baiDangService.findTop3BaiDangByViews();
        List<BaiDang> top3BinhLuans = baiDangService.findTop3BaiDangByBinhLuan();
        model.addAttribute("top3views",top3views);
        model.addAttribute("top3BinhLuans",top3BinhLuans);
        return "layouts/homemain";
    }

    @RequestMapping(value = "/diadiem/{id}/dangbaiviet", method = RequestMethod.GET)
    public String dangBaiViet(Model model,@PathVariable String id) {

        System.out.println("ID :"+id);
        model.addAttribute("diadiem",diaDiemService.findDiaDiemById(Integer.parseInt(id)));
        return "layouts/dangbaiviet";
    }

    @RequestMapping(value = "/dangdiadiem", method = RequestMethod.GET)
    public String dangDiaDiem(Model model) {

        return "dangdiadiem";
    }



    @PostMapping("/savediadiem")
    public String saveDiaDiem(@RequestParam("tendiadiem") String tendiadiem,@RequestParam("tinhthanhname") String tinhthanhname,@RequestParam("giohoatdong") String giohoatdong,@RequestParam("giodongcua") String giodongcua,@RequestParam("phone") int phone, @RequestParam("giamin") int giamin,@RequestParam("giamax") int giamax,@RequestParam("diachi") String diachi, @RequestParam("kinhdo") String kinhdo,@RequestParam("vido") String vido,@RequestParam("noidung") String noidung, @RequestParam("pro-image") List<MultipartFile> photos,Principal principal) throws ParseException {

        System.out.println(photos.get(0).getOriginalFilename());
        System.out.println(giohoatdong);
        System.out.println(giodongcua);
        DiaDiem diaDiem = new DiaDiem();
        diaDiem.setTenDiaDiem(tendiadiem);
        diaDiem.setGiamin(giamin);
        diaDiem.setGiamax(giamax);
        Date start = new  SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(giohoatdong);
        Date end = new  SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(giodongcua);
        diaDiem.setGioHoatDong(start);
        diaDiem.setGioDongCua(end);
        diaDiem.setKinhDo(kinhdo);
        diaDiem.setViDo(vido);
        diaDiem.setDiaChi(diachi);
        diaDiem.setNoiDung(noidung);
        diaDiem.setSdt(phone);
        String userName = principal.getName();
        Account user  = userService.findByUserName(userName);
        diaDiem.setUserId(user);
        List<Image > images = new ArrayList<>();
        int i =0;
        for(MultipartFile photo : photos){
            Image image = new Image();
            i++;
            int series = imageService.getLatestIdImage()+i;
            image.setImageUrl(series+"_"+photo.getOriginalFilename());
            image.setDiaDiemImage(diaDiem);
            images.add(image);
            imageService.saveImageToUploads(photo,series);
        }
        TinhThanh tinhThanh = tinhThanhService.getTinhThanhById(Integer.parseInt(tinhthanhname));
        diaDiem.setTinhThanh(tinhThanh);
        diaDiem.setListImage_DiaDiem(images);
        diaDiemService.saveDiaDiem(diaDiem);
        return "success";
    }


    @PostMapping("/savebinhluan/{id}")
    public String saveBinhluan(@RequestParam("tieude") String tieude, @RequestParam("diemdanhgia") Float diemdanhgia,@RequestParam("noidung") String noidung,@PathVariable String id, Principal principal){
        // thay the ID bang Pathvariable ID

         BaiDang baiDang =  baiDangService.findBaiDangById(Integer.parseInt(id));

        BinhLuan binhLuan = new BinhLuan();
        binhLuan.setTieude(tieude);
        binhLuan.setDiemdanhgia(diemdanhgia);
        binhLuan.setNoidung(noidung);
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        binhLuan.setThoigian(ts);
        binhLuan.setUserId(userService.findByUserName(principal.getName()));
        binhLuan.setBaiDang(baiDang);
        binhLuanService.saveBinhLuan(binhLuan);

        return "redirect:/chitietbaidang/"+id;

    }

    @PostMapping("/updatenoidung/{id}")
    public String updateNoiDung(@RequestParam("noidungchinhsua") String noidungchinhsua,@PathVariable String id, Principal principal){
        // thay the ID bang Pathvariable ID

        BaiDang baiDang =  baiDangService.findBaiDangById(Integer.parseInt(id));
        baiDang.setNoiDung(noidungchinhsua);
        baiDangService.saveBaiDang(baiDang);
        return "redirect:/chitietbaidang/"+id;

    }

    @PostMapping("/updatepassword/{id}")
    public String updatePassword(@RequestParam("oldpassword") String oldpassword,@RequestParam("newpassword") String newpassword,@RequestParam("confirmpassword") String confirmpassword,@PathVariable String id, Principal principal){

        Account account = userService.findByUserId(Long.parseLong(id));
        System.out.println(account.getPassWord());
        System.out.println(oldpassword);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode(newpassword));
        System.out.println(newpassword);
        System.out.println(confirmpassword);
        System.out.println(encoder.matches(oldpassword,account.getPassWord()));
       if(encoder.matches(oldpassword,account.getPassWord()) == false || newpassword.compareTo(confirmpassword) != 0){
           return "error_changepass";
       }else{
           account.setPassWord(EncrytedPasswordUtils.encrytedPassword(newpassword));
           userService.saveUser(account);
           return "redirect:/profile";
       }

    }

    @PostMapping("/deletebaiviet/{id}")
    public String deleteBaiViet(@PathVariable String id, Principal principal){
        // thay the ID bang Pathvariable ID
        BaiDang baiDang =  baiDangService.findBaiDangById(Integer.parseInt(id));
        baiDangService.deleteBaiDang(baiDang);
        return "redirect:/mybaidang";

    }

    @PostMapping("/giahanbaiviet/{id}")
    public String giaHanBaiViet(@PathVariable String id, Principal principal){
        // thay the ID bang Pathvariable ID
        BaiDang baiDang =  baiDangService.findBaiDangById(Integer.parseInt(id));
        baiDang.setFlag(1);
        System.out.println("FLAG: "+baiDang.getFlag());
        baiDangService.saveBaiDang(baiDang);
        return "redirect:/chitietbaidang/"+id;

    }


    @RequestMapping(value = {"/chitietdiadiem/{id}"},method =  RequestMethod.GET)
    public String DetailMain(Model model,@PathVariable String id){
        DiaDiem diaDiem = diaDiemService.findDiaDiemById(Integer.parseInt(id));
        List<Image> img = imageService.findAllImageByDiaDiem(diaDiem);
        List<BaiDang> baidanglienquan = baiDangService.findAllBaiDangByDiaDiem(diaDiem);
        model.addAttribute("img",img);
        model.addAttribute("diadiem",diaDiem);
        model.addAttribute("baidanglienquan",baidanglienquan);
        return "layouts/detailmain";
    }

//    @RequestMapping("/error")
//    public String handleError(HttpServletRequest request) {
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//
//        if (status != null) {
//            Integer statusCode = Integer.valueOf(status.toString());
//
//            if(statusCode == HttpStatus.NOT_FOUND.value()) {
//                return "404";
//            }
//            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//                return "500";
//            }
//        }
//        return "403Page";
//    }

    @RequestMapping(value = {"/chitietbaidang/{id}"},method =  RequestMethod.GET)
    public String ChiTietBaiDang(Model model, Principal principal,@PathVariable String id){
        BaiDang baiDang = baiDangService.findBaiDangById(Integer.parseInt(id));
//        System.out.println("SO LUOT XEM");


        List<Image> img = imageService.findAllImageByBaiDang(baiDang);
        Account account = userService.findByUserName(principal.getName());
        List<BinhLuan> binhLuanList = binhLuanService.getAllBinhLuanOfBaiDang(Integer.parseInt(id));

        Date now = new Date();
        Timestamp ts = new Timestamp(now.getTime());
        boolean check = false;
        if(ts.before(baiDang.getThoiGianHetHan())){
            check = true;
//            System.out.println("CHUA HET HAN");
        }
        baiDang.setSoLuotXem(baiDang.getSoLuotXem()+1);
        baiDangService.saveBaiDang(baiDang);
        boolean checkauthor =false;
        if(baiDang.getUserId() == account){
            checkauthor = true;
        }

        List<BaiDang> baidanglienquan = baiDangService.findListBaiDangByDanhMuc(baiDang.getDanhMuc());
        model.addAttribute("img",img);
        model.addAttribute("baidang",baiDang);
        model.addAttribute("account",account);
        model.addAttribute("listbinhluans",binhLuanList);
        model.addAttribute("check",check);
        model.addAttribute("checkauthor",checkauthor);
        model.addAttribute("baidanglienquan",baidanglienquan);

        return "layouts/baidangchitiet";
    }

    @RequestMapping(value = "/mybaidang",method = RequestMethod.GET)
    public String myBaiDang(Model model ,Principal principal){
        Account account = userService.findByUserName(principal.getName());
        model.addAttribute("account",account);

        return "layouts/myBaiDang";
    }

    @RequestMapping(value = {"/listing"},method =  RequestMethod.GET)
    public String Listing(){

        return "layouts/listingmain";
    }
    //main


    @RequestMapping(value = {"/signup"},method =  RequestMethod.GET)
    public String registerForm(Model model) {

        model.addAttribute("user", new Account());
        return "registerForm";
    }

    @RequestMapping(value = {"/signup2"},method =  RequestMethod.GET)
    public String registerForm2(Model model) {

        model.addAttribute("user", new Account());
        return "signup";
    }



    @PostMapping("/register")
    public String registerUser(@Valid  Account account, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "registerForm2";
        }
        if(userService.isUserPresent(account.getUserName())) {
            model.addAttribute("exist",true);
            System.out.println("TAI KHOAN DA CO NGƯƠI SU DUNG");
            return "redirect:/signup2";
        }else{
            userService.createUser(account);
            return "success";
        }


    }

    @RequestMapping(value = {"/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage2(Model model) {

        return "login";
    }


    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }
        return "403";
    }


    // Profile Page
    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public String getProFile(Model model,Principal principal){
        String userName = principal.getName();
        Account user = userService.findByUserName(userName);
        boolean check = false;
        if(user.getListUserImages().size() == 0){
            check = false;

        }else{
            check = true;
        }
        model.addAttribute("userInfor",user);
        model.addAttribute("check",check);
        return "layouts/userInfor";
    }
    @RequestMapping(value = "/searchDiaDiem",method = RequestMethod.GET)
    public String searchDiaDiem(Model model, @Param(value = "tendiadiem") String tendiadiem){

        System.out.println(tendiadiem);
        List<DiaDiem> diaDiemList = diaDiemService.findAllListByName(tendiadiem);
        List<BaiDang> baiDangList = baiDangService.findAllBaiDangSearchByName(tendiadiem,"NUL");
        model.addAttribute("diaDiemList",diaDiemList);
        model.addAttribute("baiDangList",baiDangList);


        return "layouts/searchDiaDiem";
    }

    @RequestMapping(value = "/searchDanhMuc/{id}",method = RequestMethod.GET)
    public String searchDanhMuc(Model model,@PathVariable String id, @RequestParam(value = "tendiadiem",required = false) String tendiadiem ){
        if(tendiadiem == null || tendiadiem.length()==0){
            DanhMuc d =  danhMucService.findDanhMucById(Integer.parseInt(id));
            List<DiaDiem> diaDiemList = diaDiemService.findAllListByName("NUL");
            List<BaiDang> baiDangList = baiDangService.findAllBaiDangSearchByName("NUL",d.getTenDanhMuc());
            model.addAttribute("diaDiemList",diaDiemList);
            model.addAttribute("baiDangList",baiDangList);
        }else{
            List<DiaDiem> diaDiemList = diaDiemService.findAllListByName(tendiadiem);
            List<BaiDang> baiDangList = baiDangService.findAllBaiDangSearchByName(tendiadiem,"NUL");
            model.addAttribute("diaDiemList",diaDiemList);
            model.addAttribute("baiDangList",baiDangList);
        }

        return "layouts/searchDiaDiem";
    }

    @RequestMapping(value = "/searchDiaDiem/{id}",method = RequestMethod.GET)
    public String searchDiaDiem2(Model model,@PathVariable String id, @RequestParam(value = "tendiadiem",required = false) String tendiadiem ){
        if(tendiadiem == null || tendiadiem.length()==0){
            TinhThanh t =  tinhThanhService.getTinhThanhById(Integer.parseInt(id));
            List<DiaDiem> diaDiemList = diaDiemService.findAllListByName(t.getTen());
            List<BaiDang> baiDangList = baiDangService.findAllBaiDangSearchByName(t.getTen(),"NUL");
            model.addAttribute("diaDiemList",diaDiemList);
            model.addAttribute("baiDangList",baiDangList);
        }else{
            List<DiaDiem> diaDiemList = diaDiemService.findAllListByName(tendiadiem);
            List<BaiDang> baiDangList = baiDangService.findAllBaiDangSearchByName(tendiadiem,"NUL");
            model.addAttribute("diaDiemList",diaDiemList);
            model.addAttribute("baiDangList",baiDangList);
        }

        return "layouts/searchDiaDiem";
    }




    public String searchText1 = "";
    public String searchText2 = "";
    @RequestMapping(value = "/searchDetail",method = RequestMethod.GET)
    public String searchDetail2(Model model,@RequestParam(value = "test1",required=false) String test1, @RequestParam(value = "test2",required=false) String test2){
        List<BaiDang> baidangsearchs = new ArrayList<>();
        System.out.println("GET");
        System.out.println("TEST1:" +test1);
        System.out.println("TEST2: "+test2);
        System.out.println("SEARCHTEXT1 BEF: "+searchText1);
        System.out.println("SEARCHTEXT2: BEF"+searchText2);

        if((test1 == null && test2 == null) || (test1.length()==0 && test2.length()==0)){
            if(searchText1.length()!=0 && searchText2.length() == 0){
                baidangsearchs = baiDangService.findAllBaiDangSearchByName(searchText1,"NUL");
            }else if(searchText1.length() == 0 && searchText2.length() !=0){
                baidangsearchs = baiDangService.findAllBaiDangSearchByName("NUL",searchText2);
            }
            else if (searchText1.length() != 0 && searchText2.length() != 0){
                baidangsearchs = baiDangService.findAllBaiDangSearchByName(searchText1,searchText2);
            }else{
                baidangsearchs = baiDangService.findAllBaiDangSearchByName("","");
            }
        }else if(test1.length()!=0 && test2.length()==0){
            searchText1 = test1;
            searchText2 = "";
            baidangsearchs = baiDangService.findAllBaiDangSearchByName(test1,"NUL");
        }else if(test1.length() == 0 && test2.length()!=0){
            searchText1 = "";
            searchText2 =test2;
            baidangsearchs = baiDangService.findAllBaiDangSearchByName("NUL",test2);
        }
        else{
            searchText1 =test1;
            searchText2 = test2;
            baidangsearchs = baiDangService.findAllBaiDangSearchByName(test1,test2);
        }
        model.addAttribute("baidangsearchs",baidangsearchs);
        System.out.println("SEARCHTEXT1 AF: "+searchText1);
        System.out.println("SEARCHTEXT2: AF"+searchText2);
        return "layouts/searchDetail";
    }

    @RequestMapping(value = "/searchDetail",method = RequestMethod.POST)
    public String searchDetail(Model model,@RequestParam(value = "test1",required=false) String test1, @RequestParam(value = "test2",required=false) String test2){
        List<BaiDang> baidangsearchs = new ArrayList<>();
        System.out.println("POST");
        System.out.println("TEST1:" +test1);
        System.out.println("TEST2: "+test2);
        System.out.println("SEARCHTEXT1 BF: "+searchText1);
        System.out.println("SEARCHTEXT2: BF"+searchText2);
        if((test1 == null && test2 == null) || (test1.length()==0 && test2.length()==0)){
            System.out.println("CA 2 DEU NULL ");
            if(searchText1.length()!=0 && searchText2.length() == 0){
                baidangsearchs = baiDangService.findAllBaiDangSearchByName(searchText1,"NUL");
            }else if(searchText1.length() == 0 && searchText2.length() !=0){
                baidangsearchs = baiDangService.findAllBaiDangSearchByName("NUL",searchText2);
            }
            else if (searchText1.length() != 0 && searchText2.length() != 0){
                baidangsearchs = baiDangService.findAllBaiDangSearchByName(searchText1,searchText2);
            }else{
                baidangsearchs = baiDangService.findAllBaiDangSearchByName("","");
            }
        }else if(test1.length()!=0 && test2.length()==0){
            System.out.println("CA 2 NULL ");
            searchText1 = test1;
            searchText2="";
            baidangsearchs = baiDangService.findAllBaiDangSearchByName(test1,"NUL");
        }else if(test1.length() == 0 && test2.length()!=0){
            System.out.println("CA 1 NULL");
            searchText2 =test2;
            searchText1="";
            baidangsearchs = baiDangService.findAllBaiDangSearchByName("NUL",test2);
        }
         else{
             System.out.println("CA 2 DEU KHONG NULL ");
             searchText1 =test1;
             searchText2 = test2;
            baidangsearchs = baiDangService.findAllBaiDangSearchByName(test1,test2);
        }
        model.addAttribute("baidangsearchs",baidangsearchs);
        System.out.println("SEARCHTEXT1 AF: "+searchText1);
        System.out.println("SEARCHTEXT2: AF"+searchText2);
        return "redirect:/searchDetail";
    }


    @PostMapping("/postavatar/{id}/saveavatar")
    public String changeAvatar(Model model, @PathVariable String id ,@RequestParam("pro-image") List<MultipartFile> photos){
            Account account = userService.findByUserId(Long.parseLong(id));
            List<Image > images = new ArrayList<>();
        int i =0;
        for(MultipartFile photo : photos){
            Image image = new Image();
            i++;
            int series = imageService.getLatestIdImage()+i;
            image.setImageUrl(series+"_"+photo.getOriginalFilename());
            image.setUserImage(account);
            images.add(image);

            imageService.saveImageToUploads(photo,series);
        }
        account.setListUserImages(images);
        userService.saveUser(account);


        return "redirect:/profile";
    }

}
