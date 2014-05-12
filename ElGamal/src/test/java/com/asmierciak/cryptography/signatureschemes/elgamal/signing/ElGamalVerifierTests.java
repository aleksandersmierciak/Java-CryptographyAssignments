package com.asmierciak.cryptography.signatureschemes.elgamal.signing;

import com.asmierciak.cryptography.signatureschemes.elgamal.keys.ElGamalPublicKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(Parameterized.class)
public class ElGamalVerifierTests {
    private BigInteger input;

    private final ElGamalSignature signature;

    private final ElGamalVerifier verifier;

    public ElGamalVerifierTests(ElGamalPublicKey publicKey, ElGamalSignature signature, BigInteger input) {
        this.signature = signature;
        this.input = input;

        verifier = new ElGamalVerifier(publicKey);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        ElGamalPublicKey smallPublicKey = new ElGamalPublicKey(BigInteger.valueOf(4663), BigInteger.valueOf(3605), BigInteger.valueOf(3831));
        ElGamalPublicKey mediumPublicKey = new ElGamalPublicKey(
                new BigInteger("1230186684530117755130494958384962720772853569595334792197322452151726400507263657518745202199786469389956474942774063845925192557326303453731548268507917026122142913461670429214311602221240479274737794080665351419597459856902143413"),
                new BigInteger("178530505861634674756617917210668419492586289623607229616955011560049646148685373630841737744868677832680706866777032422627596011085224463063903982759011429881144684069684685659195817399528287283642149768373178525608379048644428023"),
                new BigInteger("297862634128839561820402180654517022749165787047341397566874391927082725619027197086037627933750271813515457359699153060665365820233656809052566400659870364763803093273626657949480173367041904711393216626086558798205621377354236130"));
        ElGamalPublicKey largePublicKey = new ElGamalPublicKey(
                new BigInteger("25195908475657893494027183240048398571429282126204032027777137836043662020707595556264018525880784406918290641249515082189298559149176184502808489120072844992687392807287776735971418347270261896375014971824691165077613379859095700097330459748808428401797429100642458691817195118746121515172654632282216869987549182422433637259085141865462043576798423387184774447920739934236584823824281198163815010674810451660377306056201619676256133844143603833904414952634432190114657544454178424020924616515723350778707749817125772467962926386356373289912154831438167899885040445364023527381951378636564391212010397122822120720357"),
                new BigInteger("18789957311924561760526805272785424944057085730458773402275465940540323552805751563430721740457676003798173526039653032794281801942953763267142126746774890756416198629885878187789263002494817616098863121036833390546140448772320449751113731841213453920827290513126545699209078860612928528325394498131619029845973843354234496451032224138369280774678975117256745445563787188500487434535290199252720632542189527654992199772077420440758759385536889480423511224973137723928488445417055438703194990104611951002494247783905974296441628215417570301108843660486107622454097248849535217576097110562042211090476349569401352960062"),
                new BigInteger("13877807406642923092457670827505860645542644049211917860891480785029816764584728533650329535449273631937286879409218802551914658843912530079033225091042327886809549634210248670873623227794290535543766606334786977341613911033377831761749439965395220042931960034647925113674475964451425575389627602068061710380562594811467258231203416172231945296044344892922621424593987132998252004038368667752086162912968790800041795401090962741396677140098965366542269336768268162734667071371347553852424927982004531354113171206225882912281237957922411604221568343334472627805712715359230434748455375292424912879430440098611814868222"));

        // "Ala ma kota"
        BigInteger message1 = new BigInteger("435547623972009042387221878687981899647773248766318257271173050301525056529400623692496442046820");
        // "The quick brown fox jumps over the lazy dog"
        BigInteger message2 = new BigInteger("420522168198925353602494955315695890553608393966403546920929821953767356706598818287069947900210");
        // "Zażółć gęślą jaźń"
        BigInteger message3 = new BigInteger("468916691242576384263065331806697524728300735263052717145002597509402505827034369095083310016099");

        // Test case data found below consist of
        // ElGamal public and private keys
        // and a message hash encoded using AsciiEncoder.
        Object[][] data = new Object[][]
                {
                        { smallPublicKey, new ElGamalSignature(BigInteger.valueOf(2089), BigInteger.valueOf(2301)), message1 },
                        { smallPublicKey, new ElGamalSignature(BigInteger.valueOf(3514), BigInteger.valueOf(576)), message2 },
                        { smallPublicKey, new ElGamalSignature(BigInteger.valueOf(2725), BigInteger.valueOf(418)), message3 },

                        { mediumPublicKey, new ElGamalSignature(new BigInteger("468467025449761865753241070034986420073158147300437294250717794904271341782195716931743122922324131240554168200502182913775281259883736036577144101651069664104815550006312791252506020489166379171565812899233560812347606694717436697"), new BigInteger("564461046908270710248261296443397471060955440091761908409983557341024335908325741148626849442901174317312089587726498648231794093462764701332392390098455325967309106657020349048079703458543149432849479266448508419937964367930054416")), message1 },
                        { mediumPublicKey, new ElGamalSignature(new BigInteger("936534526374524324210934360121746007203440524383203576170063993255382754443306804101027888638953420099860455451220671144150275367465809194931346238972126867000836378691481767292038357007106219486425555686136508026646907247402687624"), new BigInteger("186251453103191307127950097860920541461158868313885891437168560129978756765901098499177539551923093911213038648207087567351991639711136513279971874476805059071236236592831400457042038025399660186450176194430191150642422671836178138")), message2 },
                        { mediumPublicKey, new ElGamalSignature(new BigInteger("166975356753122465547913747325598876576122057607042811515925415268546654926189906084677877464037388626250260726189539073852067317100664764899862624676806152658105011260121049624482205402899391047180324825562117489342843091318417249"), new BigInteger("784763385720605106666651868132934541939325322657125429898274293254768721818958563559596538840985900395971071655124420438885070105212463164420098738935047551345575774335894568375583409353513280109428691650093326489515599356810584779")), message3 },

                        { largePublicKey, new ElGamalSignature(new BigInteger("3114348912353427767092729851882873483499319892244504653355782372798233734406759615732689115513400272518220704864489100074026557214829122003424914648127565067404893915296258504685772211194792064625074076679690673999664605583115047495579084327246582175061639392639684820517545609146425118882497767563322788996917787829054169593209561221666028320186397119467377241586105042456099227821705190924304722061209208760667530846124746527833708145713934306174591363491143038106036507262529120114637594296462503594342752963606007091567244836344464688562562467178290894098285982263924733511174454338586064828968868247227380678340"), new BigInteger("20047347034676011374016008038413137097588901007021085183698273005088415632115750743667725668584863933642848968598486108478450747748017363261354391722619288716686985737353591786869861766429367831124290102162956561806505005932720013603751702041484378954355699737128449410580990110677162586564523866518204235953548222129653695726450884892236953705207175914762492443092579248024830989051228431654372125872826732508968212715198025384216867380032426543652502135244952413912387994830486266895106881582833338465236983946683556519972936509157976824244925467758423358689899085249292316560449491799096806098592869836199336490608")), message1 },
                        { largePublicKey, new ElGamalSignature(new BigInteger("3943128817234100807479034376629305718502919831548057744352516691545064979088551092487090844245230723505351963428613619341808413872212629664530565115959397482290658511274459401718970282587917290982751260553756991126909717178526108549821794365969085627856974153675259774150044121831930023514911963802962922650153492952409730452421558536946871672986124453834920669194098288737129862281579080927551052070056448930479604590085223265709527701325098465390803022639725283716243911958687508474123742069201453685700332900843280859435331598779145424976625450441471971038139357601015916605689916655000461771130646937500120640838"), new BigInteger("1127717840995306228511046059581382457378989530142903814131963424447211264772160456467144320444632665412157542941767885354497049544226860117530622211312489577000964223114908119609723128090536846617506027707807703701173931004047789984965338848342003655016895873271058416013057904487479579370423304723232593034674662064694100705020314466491619291313140587316448990424151078142790635966063920322357880631390938944125736837940067956350209653772611052232209613455391151335253740737348838854426877936239786012732907043593382197040534533071925338563627518568519240774508591387932725081519374486055154998059488152599423167818")), message2 },
                        { largePublicKey, new ElGamalSignature(new BigInteger("8243194547014151425089367691980534816028449509837137062948942510101682665143362642045933266615211236224012194376685025206647717514501045937578120146937149652357288822900708682990715726240117488936811501229796561904550328901294732361720189619599971808051709379841929618082495350714185482088833123990898091144417277523315734521975144113456959927792138166680408525665793574704120520654183416545043788656468143760245297918638324163218446164079603115291667338876762858414290384294807127176536775258963380013340717460645099366085259717523465467773549753454336161727838174479712098962827875008454286404978333588049565018499"), new BigInteger("1396952944633322537043153753958649878787028013458411992255817415900497190566576854253569095170731478384660866563924953789367767124909198929842804771967907054857544441162236814725968422433828029633815713697093347234195696397728633733467042615132704354308185407147159467138921111212210439948306655114610716672516227295823487137744995954880228844912082938820324222431200969055776285112581832244019385551470746802023701179826058456063057867212132553547755319979585931276493046534671130718092136914492845982506878767843335663713223574014633732871934031819429858684924284780599325821621550737413223624632578880817301350857")), message3 },
                };
        return Arrays.asList(data);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfSignatureIsNull() {
        verifier.verify(null, input);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfInputIsNull() {
        verifier.verify(new ElGamalSignature(BigInteger.ONE, BigInteger.ONE), null);
    }

    @Test
    public void testInputMessageIsVerified() {
        assertThat(verifier.verify(signature, input), is(true));
    }
}
